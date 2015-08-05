package com.demo.java.web.role.controller;

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

import com.demo.java.role.entity.Role;
import com.demo.java.role.service.RoleService;
import com.demo.java.user.entity.User;
import com.demo.java.web.common.controller.AbstractController;
import com.demo.java.web.common.vo.PageVo;

@Controller
@RequestMapping("role")
public class RoleController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Resource
    RoleService roleService;

    @RequestMapping("toList")
    public ModelAndView toList() {
        return new ModelAndView("role/list");
    }

    @RequestMapping("list")
    @ResponseBody
    public PageVo<Role> list(int curPage, int pageSize, HttpServletRequest request) {
        List<Role> result = roleService.pageList(curPage, pageSize);
        int totalResults = roleService.size();
        return new PageVo<Role>(curPage, pageSize, totalResults, result);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("role/input");
        randomUUID(request);
        model.addObject("action", "/role/add");
        model.addObject("submit", "新增");
        return model;
    }

    @RequestMapping("edit/{id}")
    public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("role/input");
        randomUUID(request);
        Role role = roleService.get(id);
        model.addObject("role", role);
        model.addObject("action", "/role/update");
        model.addObject("submit", "编辑");
        return model;
    }

    @RequestMapping("get/{id}")
    public ModelAndView get(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("role/input");
        randomUUID(request);
        Role role = roleService.get(id);
        model.addObject("role", role);
        return model;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView add(Role role, String UUID, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/role/toAdd");
        if (!checkUUID(UUID, request)) {
            return model;
        }
        User u = (User) request.getSession().getAttribute("user");
        int res = roleService.add(role, u);
        if (res <= 0) {
            return model;
        }
        model.setViewName("redirect:/role/toList");
        return model;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ModelAndView update(Role role, String UUID, HttpServletRequest request) {
        ModelAndView model = new ModelAndView("redirect:/role/edit/" + role.getId());
        if (!checkUUID(UUID, request)) {
            return model;
        }
        User u = (User) request.getSession().getAttribute("user");
        int res = roleService.update(role, u);
        if (res <= 0) {
            return model;
        }
        model.setViewName("redirect:/role/toList");
        return model;
    }
}