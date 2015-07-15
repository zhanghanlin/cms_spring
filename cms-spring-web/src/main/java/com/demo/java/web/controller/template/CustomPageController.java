package com.demo.java.web.controller.template;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.demo.java.dict.Status;
import com.demo.java.entity.CustomPage;
import com.demo.java.service.CustomPageService;
import com.demo.java.utils.Constants;
import com.demo.java.utils.file.FileConstants;
import com.demo.java.utils.file.FileUtils;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.controller.AbstractController;

@Controller
@RequestMapping("/template/page")
public class CustomPageController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(CustomPageController.class);

    @Resource
    CustomPageService customPageService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView();
        List<CustomPage> list = customPageService.getList();
        model.addObject("list", list);
        model.setViewName("template/page/list");
        return model;
    }

    @RequestMapping(value = "/toAdd")
    public ModelAndView toAdd() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/page/input");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, String name, String data, String path) {
        String fileName = FileConstants.PAGE_VM_PREFIX + String.valueOf(((new Date()).getTime() / 1000));
        if (StringUtils.isNotBlank(data)) {
            try {
                data = URLDecoder.decode(data, Constants.ENCODING);
            } catch (UnsupportedEncodingException e) {
                LOG.error("UnsupportedEncodingException error :{}", e.getMessage(), e);
            }
            data = StringUtils.unicode2String(data);
        }
        FileUtils.createFile(fileName + ".vm", FileConstants.PAGE_VM_PATH, data);
        CustomPage t = new CustomPage();
        t.setCreatedAt(new Date());
        t.setCreatedBy("System");
        t.setFileName(fileName);
        t.setName(name);
        t.setIsRefresh(0);
        t.setPath(path);
        t.setStatus(Status.NORMAL);
        customPageService.save(t);
        return "/template/page/list";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView toEdit(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("t", customPageService.get(id));
        model.setViewName("template/page/input");
        return model;
    }

    @RequestMapping(value = "/update/{id}")
    public ModelAndView update(@PathVariable Long id, @RequestParam String name, @RequestParam String data, @RequestParam String path) {
        ModelAndView model = new ModelAndView();
        CustomPage t = customPageService.get(id);
        t.setName(name);
        t.setPath(path);
        int res = customPageService.update(t);
        if (res > 0) {
            String vm = FileConstants.PAGE_VM_PATH + "/" + t.getFileName() + ".vm";
            try {
                FileUtils.writeStringToFile(new File(vm), data);
            } catch (IOException e) {
                LOG.error("IOException error :{}", e.getMessage(), e);
            }
        }
        model.setViewName("template/page/list");
        return model;
    }

    @RequestMapping(value = "/get/{id}")
    @ResponseBody
    public JSONObject get(@PathVariable Long id) {
        CustomPage t = customPageService.get(id);
        JSONObject o = JSONObject.parseObject(t.toJSON());
        String vm = FileConstants.PAGE_VM_PATH + "/" + t.getFileName() + ".vm";
        try {
            String data = FileUtils.readFileToString(new File(vm), Constants.ENCODING);
            o.put("data", data);
        } catch (IOException e) {
            LOG.error("getData error : {}", e.getMessage(), e);
        }
        return o;
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public int delete(@PathVariable Long id) {
        return customPageService.delete(id);
    }
}