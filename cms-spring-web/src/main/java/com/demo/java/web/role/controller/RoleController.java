package com.demo.java.web.role.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.role.entity.Role;
import com.demo.java.role.service.RoleService;
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
}