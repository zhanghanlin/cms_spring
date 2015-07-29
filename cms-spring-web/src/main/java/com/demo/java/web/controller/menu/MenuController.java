package com.demo.java.web.controller.menu;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.common.dict.Status;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.menu.utils.MenuMemory;
import com.demo.java.menu.utils.MenuNode;
import com.demo.java.user.entity.User;
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
        return new ModelAndView("menu/manage");
    }

    @RequestMapping("/tree/_all")
    @ResponseBody
    public MenuNode allTree(HttpServletRequest request) {
        return menuService.menuTree(Status.ALL);
    }

    @RequestMapping("/tree")
    @ResponseBody
    public MenuNode tree(HttpServletRequest request) {
        MenuNode node = new MenuNode();
        User t = (User) request.getSession().getAttribute("user");
        if (t != null) {
            node = MenuMemory.get(t.getId());
        }
        return node;
    }
}