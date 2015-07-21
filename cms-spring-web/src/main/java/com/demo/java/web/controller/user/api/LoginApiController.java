package com.demo.java.web.controller.user.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.controller.AbstractController;
import com.demo.java.web.cookie.LoginCookieUtils;
import com.demo.java.web.response.ResponseContent;
import com.demo.java.web.response.UserResponseEnum;

@Controller
@RequestMapping("/api")
public class LoginApiController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(LoginApiController.class);

    @Resource
    UserService userService;

    @RequestMapping(value = "/valid", method = RequestMethod.POST)
    public ResponseContent<User> valid(HttpServletRequest request, HttpServletResponse response) {
        boolean valid = LoginCookieUtils.validLogin(request, response);
        if (valid) {
            return new ResponseContent<User>(UserResponseEnum.SUCCESS);
        }
        return new ResponseContent<User>(UserResponseEnum.ERROR);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<User> login(@RequestParam String userName, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
                return new ResponseContent<User>(UserResponseEnum.ERROR_PARAM_NULL);
            }
            User t = userService.valid(userName, password);
            if (t != null) {
                LoginCookieUtils.loginSuccess(t, request, response);
                return new ResponseContent<User>(UserResponseEnum.SUCCESS, t);
            } else {
                return new ResponseContent<User>(UserResponseEnum.ERROR_USERNAME_PWD);
            }
        } catch (Exception e) {
            LOG.error("login > userName : {}, error : {}", userName, e.getMessage(), e);
        }
        return new ResponseContent<User>(UserResponseEnum.ERROR);
    }
}
