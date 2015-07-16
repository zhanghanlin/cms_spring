package com.demo.java.web.controller.template.api;

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

import com.demo.java.common.dict.Status;
import com.demo.java.template.entity.CustomPage;
import com.demo.java.template.service.CustomPageService;
import com.demo.java.utils.Constants;
import com.demo.java.utils.file.FileConstants;
import com.demo.java.utils.file.FileUtils;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.controller.AbstractController;
import com.demo.java.web.response.ResponseContent;
import com.demo.java.web.response.ResponseEnum;

@Controller
@RequestMapping("/template/page/api")
public class CustomPageApiController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(CustomPageApiController.class);

    @Resource
    CustomPageService customPageService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ResponseContent<List<CustomPage>> list() {
        try {
            List<CustomPage> list = customPageService.getList();
            return new ResponseContent<List<CustomPage>>(ResponseEnum.SUCCESS, list);
        } catch (Exception e) {
            LOG.error("list error :{}", e.getMessage(), e);
        }
        return new ResponseContent<List<CustomPage>>(ResponseEnum.ERROR);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<CustomPage> add(HttpServletRequest request, String name, String data, String path) {
        String fileName = FileConstants.PAGE_VM_PREFIX + String.valueOf(((new Date()).getTime() / 1000));
        if (StringUtils.isNotBlank(data)) {
            try {
                data = URLDecoder.decode(data, Constants.ENCODING);
            } catch (UnsupportedEncodingException e) {
                LOG.error("UnsupportedEncodingException error :{}", e.getMessage(), e);
                return new ResponseContent<CustomPage>(ResponseEnum.ERROR);
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
        try {
            customPageService.save(t);
            return new ResponseContent<CustomPage>(ResponseEnum.SUCCESS, t);
        } catch (Exception e) {
            LOG.error("add custompage error:{}", e.getMessage(), e);
        }
        return new ResponseContent<CustomPage>(ResponseEnum.ERROR);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<CustomPage> update(@PathVariable Long id, @RequestParam String name, @RequestParam String data, @RequestParam String path) {
        CustomPage t = customPageService.get(id);
        t.setName(name);
        t.setPath(path);
        try {
            int res = customPageService.update(t);
            if (res > 0) {
                String vm = FileConstants.PAGE_VM_PATH + "/" + t.getFileName() + ".vm";
                try {
                    FileUtils.writeStringToFile(new File(vm), data);
                    t.setData(data);
                    return new ResponseContent<CustomPage>(ResponseEnum.SUCCESS, t);
                } catch (IOException e) {
                    LOG.error("IOException error :{}", e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            LOG.error("update custompage error:{}", e.getMessage(), e);
        }
        return new ResponseContent<CustomPage>(ResponseEnum.ERROR);
    }

    @RequestMapping(value = "/get/{id}")
    @ResponseBody
    public ResponseContent<CustomPage> get(@PathVariable Long id) {
        try {
            CustomPage t = customPageService.get(id);
            String vm = FileConstants.PAGE_VM_PATH + "/" + t.getFileName() + ".vm";
            String data = FileUtils.readFileToString(new File(vm), Constants.ENCODING);
            t.setData(data);
            return new ResponseContent<CustomPage>(ResponseEnum.SUCCESS, t);
        } catch (IOException e) {
            LOG.error("get error : {}", e.getMessage(), e);
        }
        return new ResponseContent<CustomPage>(ResponseEnum.ERROR);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<CustomPage> delete(@PathVariable Long id) {
        try {
            customPageService.delete(id);
            return new ResponseContent<CustomPage>(ResponseEnum.SUCCESS);
        } catch (Exception e) {
            LOG.error("delete error : {}", e.getMessage(), e);
        }
        return new ResponseContent<CustomPage>(ResponseEnum.ERROR);
    }
}