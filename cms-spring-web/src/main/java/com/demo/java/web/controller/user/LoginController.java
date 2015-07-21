package com.demo.java.web.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.web.controller.AbstractController;

@Controller
public class LoginController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login")
    public ModelAndView list(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login/login");
        return model;
    }
}