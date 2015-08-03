package com.demo.java.web.controller.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.web.controller.AbstractController;
import com.demo.java.web.controller.vo.PageVo;

@Controller
@RequestMapping("user")
public class UserController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;

    @RequestMapping("toList")
    public ModelAndView toList() {
        return new ModelAndView("user/list");
    }

    @RequestMapping("list")
    @ResponseBody
    public PageVo<User> list(int curPage, int pageSize, HttpServletRequest request) {
        List<User> result = userService.pageList(curPage, pageSize);
        int totalResults = userService.size();
        return new PageVo<User>(curPage, pageSize, totalResults, result);
    }
}