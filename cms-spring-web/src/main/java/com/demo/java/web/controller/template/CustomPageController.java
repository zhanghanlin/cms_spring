package com.demo.java.web.controller.template;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.template.service.CustomPageService;
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
        model.setViewName("template/page/list");
        return model;
    }

    @RequestMapping(value = "/add")
    public ModelAndView add() {
        ModelAndView model = new ModelAndView();
        model.setViewName("template/page/input");
        return model;
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("t", customPageService.get(id));
        model.setViewName("template/page/input");
        return model;
    }
}