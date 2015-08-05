package com.demo.java.web.menu.controller;

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

import com.demo.java.common.dict.Status;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.menu.utils.MenuMemory;
import com.demo.java.menu.utils.MenuNode;
import com.demo.java.user.entity.User;
import com.demo.java.web.common.controller.AbstractController;
import com.demo.java.web.common.response.ResponseContent;
import com.demo.java.web.menu.response.MenuEnum;

@Controller
@RequestMapping("menu")
public class MenuController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Resource
    MenuService menuService;

    @RequestMapping("toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("menu/input");
        model.addObject("action", "/menu/add");
        model.addObject("submit", "新增");
        return model;
    }

    @RequestMapping("toAdd/{id}")
    public ModelAndView toAddSub(@PathVariable Long id, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("parentCode", menu.getCode());
        model.addObject("action", "/menu/add");
        List<String> list = menuService.getTreesNameByCode(menu.getCode());
        model.addObject("menuNames", list);
        model.addObject("submit", "新增");
        return model;
    }

    @RequestMapping("get/{id}")
    public ModelAndView get(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("menu", menu);
        List<String> list = menuService.getTreesNameByCode(menu.getCode());
        model.addObject("menuNames", list);
        model.addObject("submit", "");
        return model;
    }

    @RequestMapping("toList")
    public ModelAndView toList(String UUID, HttpServletRequest request) {
        return new ModelAndView("menu/list");
    }

    @RequestMapping("_all")
    @ResponseBody
    public MenuNode allTree(HttpServletRequest request) {
        return menuService.menuTree(Status.ALL);
    }

    @RequestMapping("tree")
    @ResponseBody
    public MenuNode tree(HttpServletRequest request) {
        MenuNode node = new MenuNode();
        User t = (User) request.getSession().getAttribute("user");
        if (t != null) {
            node = MenuMemory.get(t.getId());
        }
        return node;
    }

    @RequestMapping("p/{pcode}")
    @ResponseBody
    public List<Menu> getMenuByParent(@PathVariable String pcode, HttpServletRequest request) {
        return menuService.getMenuByParentCode(pcode);
    }

    @RequestMapping("maxLevel")
    @ResponseBody
    public ResponseContent<Integer> maxLevel(HttpServletRequest request) {
        return new ResponseContent<Integer>(MenuEnum.SUCCESS, menuService.maxLevel());
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add(Menu menu, String UUID, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/menu/toAdd");
        if (!checkUUID(UUID, request)) {
            return model;
        }
        User u = (User) request.getSession().getAttribute("user");
        int res = menuService.add(menu, u);
        if (res <= 0) {
            return model;
        }
        model.setViewName("redirect:/menu/toList");
        return model;
    }

    @RequestMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("menu", menu);
        model.addObject("parentCode", menu.getParentCode());
        model.addObject("action", "/menu/update");
        List<String> list = menuService.getTreesNameByCode(menu.getParentCode());
        model.addObject("menuNames", list);
        model.addObject("submit", "更新");
        return model;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ModelAndView update(Menu menu, String UUID, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/menu/toAdd");
        if (!checkUUID(UUID, request)) {
            return model;
        }
        User u = (User) request.getSession().getAttribute("user");
        int res = menuService.update(menu, u);
        if (res <= 0) {
            return model;
        }
        model.setViewName("redirect:/menu/toList");
        return model;
    }

    @RequestMapping(value = "update/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Menu> updateStatus(@RequestParam Long id, @RequestParam int status, HttpServletRequest request) {
        User t = (User) request.getSession().getAttribute("user");
        int res = menuService.updateStatus(id, status, t);
        if (res > 0) {
            return new ResponseContent<Menu>(MenuEnum.SUCCESS, menuService.get(id));
        }
        return new ResponseContent<Menu>(MenuEnum.ERROR);
    }
}