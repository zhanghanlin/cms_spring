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
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.shiro.UserUtils;
import com.demo.java.web.common.controller.AbstractController;
import com.demo.java.web.common.response.ResponseContent;
import com.demo.java.web.menu.response.MenuEnum;
import com.demo.java.web.menu.utils.MenuMemory;
import com.demo.java.web.menu.vo.MenuTree;

@Controller
@RequestMapping("menu")
public class MenuController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @Resource
    MenuService menuService;

    @Resource
    UserService userService;

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
        List<String> list = menuService.getTreesNameByCode(menu.getCode());
        model.addObject("menuNames", list);
        model.addObject("action", "/menu/add");
        model.addObject("submit", "新增");
        return model;
    }

    @RequestMapping("get/{id}")
    public ModelAndView get(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("menu", menu);
        List<String> list = menuService.getTreesNameByCode(menu.getParentCode());
        model.addObject("menuNames", list);
        return model;
    }

    @RequestMapping("toList")
    public ModelAndView toList(String UUID, HttpServletRequest request) {
        return new ModelAndView("menu/list");
    }

    @RequestMapping("_all")
    @ResponseBody
    public MenuTree allTree(HttpServletRequest request) {
        List<Menu> list = menuService.list(Status.ALL);
        return MenuTree.list2tree(list);
    }

    @RequestMapping("tree")
    @ResponseBody
    public MenuTree tree(HttpServletRequest request) {
        MenuTree tree = new MenuTree();
        String userName = UserUtils.getUserName();
        String menuKey = userName + "_m_t";
        tree = MenuMemory.get(menuKey);
        if (tree == null) {
            User t = userService.findByLogin(userName);
            if (t != null) {
                List<Menu> list = menuService.list(Status.NORMAL);
                tree = MenuTree.list2tree(list);
                MenuMemory.put(menuKey, tree);
            }
        }
        return tree;
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
    @ResponseBody
    public ResponseContent<Menu> add(Menu menu, String UUID, HttpServletRequest request) {
        if (!checkUUID(UUID, request)) {
            return new ResponseContent<Menu>(MenuEnum.ERROR);
        }
        int res = menuService.add(menu);
        if (res > 0) {
            return new ResponseContent<Menu>(MenuEnum.SUCCESS);
        }
        return new ResponseContent<Menu>(MenuEnum.ERROR);
    }

    @RequestMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
        randomUUID(request);
        ModelAndView model = new ModelAndView("menu/input");
        Menu menu = menuService.get(id);
        model.addObject("menu", menu);
        model.addObject("parentCode", menu.getParentCode());
        List<String> list = menuService.getTreesNameByCode(menu.getParentCode());
        model.addObject("menuNames", list);
        model.addObject("action", "/menu/update");
        model.addObject("submit", "更新");
        return model;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Menu> update(Menu menu, String UUID, HttpServletRequest request) {
        if (!checkUUID(UUID, request)) {
            return new ResponseContent<Menu>(MenuEnum.ERROR);
        }
        int res = menuService.update(menu);
        if (res > 0) {
            return new ResponseContent<Menu>(MenuEnum.SUCCESS);
        }
        return new ResponseContent<Menu>(MenuEnum.ERROR);
    }

    @RequestMapping(value = "update/status", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<Menu> updateStatus(@RequestParam Long id, @RequestParam int status, HttpServletRequest request) {
        int res = menuService.updateStatus(id, status);
        if (res > 0) {
            return new ResponseContent<Menu>(MenuEnum.SUCCESS, menuService.get(id));
        }
        return new ResponseContent<Menu>(MenuEnum.ERROR);
    }
}