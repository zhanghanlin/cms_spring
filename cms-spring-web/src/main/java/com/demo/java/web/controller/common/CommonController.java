package com.demo.java.web.controller.common;

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

@Controller
public class CommonController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    @Resource
    UserService userService;

    /**
     * 进入首页.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/main")
    public ModelAndView main(HttpServletRequest request) {
        randomUUID(request);
        return new ModelAndView("main");
    }

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
        randomUUID(request);
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
    public ModelAndView doLogin(@RequestParam String userName, @RequestParam String password, @RequestParam String UUID, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String sessionUUID = randomUUID(request);
        if (StringUtils.isBlank(UUID) || !UUID.equals(sessionUUID)) {
            return new ModelAndView("redirect:/login");
        }
        try {
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
                model.addObject("msg", "请输入帐号密码");
            }
            User t = userService.valid(userName, password);
            if (t != null) {
                LoginCookieUtils.setLoginCookie(t, request, response);
                return new ModelAndView("redirect:/main");
            } else {
                model.addObject("msg", "帐号或密码错误");
            }
        } catch (Exception e) {
            LOG.error("login > userName : {}, error : {}", userName, e.getMessage(), e);
        }
        return new ModelAndView("redirect:/login");
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
        return new ModelAndView("redirect:/login");
    }
}