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

import com.demo.java.dict.Status;
import com.demo.java.entity.Module;
import com.demo.java.service.ModuleService;
import com.demo.java.utils.Constants;
import com.demo.java.utils.file.FileConstants;
import com.demo.java.utils.file.FileUtils;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.controller.AbstractController;
import com.demo.java.web.response.ResponseContent;
import com.demo.java.web.response.ResponseEnum;

@Controller
@RequestMapping("/template/module")
public class ModuleController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    @Resource
    ModuleService moduleService;

    @RequestMapping(value = "/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView();
        List<Module> list = moduleService.getList();
        model.addObject("list", list);
        model.setViewName("template/module/list");
        return model;
    }

    @RequestMapping(value = "/toAdd")
    public ModelAndView toAdd() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/module/input");
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Module> add(HttpServletRequest request, String name, int moduleType, String data) {
        String fileName = FileConstants.MODULE_VM_PREFIX + String.valueOf(((new Date()).getTime() / 1000));
        if (StringUtils.isNotBlank(data)) {
            try {
                data = URLDecoder.decode(data, Constants.ENCODING);
            } catch (UnsupportedEncodingException e) {
                LOG.error("UnsupportedEncodingException error :{}", e.getMessage(), e);
                return new ResponseContent<Module>(ResponseEnum.ERROR);
            }
            data = StringUtils.unicode2String(data);
        }
        FileUtils.createFile(fileName + ".vm", FileConstants.MODULE_VM_PATH, data);
        Module t = new Module();
        t.setCreatedAt(new Date());
        t.setCreatedBy("System");
        t.setName(name);
        t.setFileName(fileName);
        t.setStatus(Status.NORMAL);
        t.setIsRefresh(0);
        t.setType(moduleType);
        try {
            moduleService.save(t);
            return new ResponseContent<Module>(ResponseEnum.SUCCESS, t);
        } catch (Exception e) {
            LOG.error("add module error:{}", e.getMessage(), e);
            return new ResponseContent<Module>(ResponseEnum.ERROR);
        }
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView toEdit(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("t", moduleService.get(id));
        model.setViewName("template/module/input");
        return model;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Module> update(@PathVariable Long id, @RequestParam String name, @RequestParam String data, @RequestParam String moduleType) {
        Module t = moduleService.get(id);
        t.setName(name);
        try {
            int res = moduleService.update(t);
            if (res > 0) {
                String vm = FileConstants.MODULE_VM_PATH + "/" + t.getFileName() + ".vm";
                try {
                    FileUtils.writeStringToFile(new File(vm), data);
                    return new ResponseContent<Module>(ResponseEnum.SUCCESS, t);
                } catch (IOException e) {
                    LOG.error("IOException error :{}", e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            LOG.error("update module error:{}", e.getMessage(), e);
        }
        return new ResponseContent<Module>(ResponseEnum.ERROR);
    }

    @RequestMapping(value = "/get/{id}")
    @ResponseBody
    public ResponseContent<Module> get(@PathVariable Long id) {
        try {
            Module t = moduleService.get(id);
            String vm = FileConstants.MODULE_VM_PATH + "/" + t.getFileName() + ".vm";
            String data = FileUtils.readFileToString(new File(vm), Constants.ENCODING);
            t.setData(data);
            return new ResponseContent<Module>(ResponseEnum.SUCCESS, t);
        } catch (IOException e) {
            LOG.error("getData error : {}", e.getMessage(), e);
        }
        return new ResponseContent<Module>(ResponseEnum.ERROR);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Module> delete(@PathVariable Long id) {
        try {
            moduleService.delete(id);
            return new ResponseContent<Module>(ResponseEnum.SUCCESS);
        } catch (Exception e) {
            LOG.error("delete error : {}", e.getMessage(), e);
        }
        return new ResponseContent<Module>(ResponseEnum.ERROR);
    }
}