package com.demo.java.web.controller.menu;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.common.dict.Status;
import com.demo.java.menu.entity.Menu;
import com.demo.java.menu.service.MenuService;
import com.demo.java.menu.utils.MenuMemory;
import com.demo.java.menu.utils.MenuNode;
import com.demo.java.user.entity.User;
import com.demo.java.web.controller.AbstractController;
import com.demo.java.web.response.MenuEnum;
import com.demo.java.web.response.ResponseContent;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Resource
    MenuService menuService;

    @RequestMapping("/toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        randomUUID(request);
        return new ModelAndView("menu/input");
    }

    @RequestMapping("/toAdd/{code}")
    public ModelAndView toAddSub(@PathVariable String code, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView();
        model.setViewName("menu/input");
        model.addObject("code", code);
        List<String> list = menuService.getTreesNameByCode(code);
        model.addObject("menuNames", list);
        return model;
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

    @RequestMapping("/tree/p/{pcode}")
    @ResponseBody
    public List<Menu> getMenuByParent(@PathVariable String pcode, HttpServletRequest request) {
        return menuService.getMenuByParentCode(pcode);
    }

    @RequestMapping("/maxLevel")
    @ResponseBody
    public ResponseContent<Integer> maxLevel(HttpServletRequest request) {
        return new ResponseContent<>(MenuEnum.SUCCESS, menuService.maxLevel());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
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
        model.setViewName("redirect:/menu/manage");
        return model;
    }
}