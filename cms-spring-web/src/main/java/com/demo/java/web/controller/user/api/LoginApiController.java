package com.demo.java.web.controller.user.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.web.controller.AbstractController;
import com.demo.java.web.response.ResponseContent;
import com.demo.java.web.response.ResponseEnum;

@Controller
@RequestMapping("/login/api")
public class LoginApiController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(LoginApiController.class);

    @Resource
    UserService userService;

    @RequestMapping(value = "/valid", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<User> valid(@RequestParam String userName, @RequestParam String password) {
        try {
            User t = userService.valid(userName, password);
            if (t != null) {
                return new ResponseContent<User>(ResponseEnum.SUCCESS, t);
            }
        } catch (Exception e) {
            LOG.error("valid user : {}, error : {}", userName, e.getMessage(), e);
        }
        return new ResponseContent<User>(ResponseEnum.ERROR);
    }
}
