package com.demo.java.web.controller.menu;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.entity.MenuTree;
import com.demo.java.menu.service.MenuService;
import com.demo.java.web.controller.AbstractController;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Resource
    MenuService menuService;

    @RequestMapping("/add")
    public ModelAndView toAdd(Menu menu, String UUID, HttpServletRequest request) {
        randomUUID(request);
        return new ModelAndView("menu/input");
    }

    @RequestMapping("/manage")
    public ModelAndView toManage(String UUID, HttpServletRequest request) {
        MenuTree tree = menuService.list();
        ModelAndView model = new ModelAndView("menu/manage");
        model.addObject("tree", tree);
        return model;
    }
}