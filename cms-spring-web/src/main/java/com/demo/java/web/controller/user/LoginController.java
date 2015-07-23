package com.demo.java.web.controller.user;

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
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.controller.AbstractController;
import com.demo.java.web.cookie.LoginCookieUtils;
import com.demo.java.web.response.ResponseContent;
import com.demo.java.web.response.UserEnum;

@Controller
public class LoginController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Resource
    UserService userService;

    /**
     * 进入登陆页面.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/login")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("login/login");
    }

    /**
     * 登陆操作.<br/>
     * 
     * @author zhanghanlin
     * @param userName
     * @param password
     * @param request
     * @param response
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseContent<User> doLogin(@RequestParam String userName, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
                return new ResponseContent<User>(UserEnum.ERROR_PARAM_NULL);
            }
            User t = userService.valid(userName, password);
            if (t != null) {
                LoginCookieUtils.setLoginCookie(t, request, response);
                return new ResponseContent<User>(UserEnum.SUCCESS, t);
            } else {
                return new ResponseContent<User>(UserEnum.ERROR_USERNAME_PWD);
            }
        } catch (Exception e) {
            LOG.error("login > userName : {}, error : {}", userName, e.getMessage(), e);
        }
        return new ResponseContent<User>(UserEnum.ERROR);
    }

    /**
     * 退出.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @param response
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        LoginCookieUtils.clearLoginCookie(request, response);
        return new ModelAndView("login/login");
    }
}