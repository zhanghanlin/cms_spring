package com.demo.java.web.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.menu.service.MenuService;
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;
import com.demo.java.utils.Constants;
import com.demo.java.utils.string.StringUtils;
import com.demo.java.web.common.system.ServerInfo;
import com.demo.java.web.common.system.ServerStatus;

@Controller
@RequestMapping
public class CommonController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    @Resource
    UserService userService;

    @Resource
    MenuService menuService;

    /**
     * 进入首页.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping
    public ModelAndView toMain(HttpServletRequest request) {
        randomUUID(request);
        return new ModelAndView("common/main");
    }

    // public ModelAndView toLogin(HttpServletRequest request) {
    // randomUUID(request);
    // return new ModelAndView("common/login");
    // }
    /**
     * 进入登陆页面.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, String uuid, Model model) {
        String error = null;
        if (request.getMethod().equals(RequestMethod.GET.toString())) {
            randomUUID(request);
        } else {
            if (checkUUID(uuid, request)) {
                String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
                if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                    error = "用户名/密码错误";
                } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                    error = "用户名/密码错误";
                } else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
                    error = "用户名/密码输入错误次数过多,请稍候再试";
                } else if (Constants.JCAPTCHA_ERROR_NAME.equals(exceptionClassName)) {
                    error = "验证码错误";
                } else if (exceptionClassName != null) {
                    error = "未知错误";
                } else {
                    if (request.getParameter("forceLogout") != null) {
                        error = "您已经被管理员强制退出，请重新登录";
                    }
                }
            }
        }
        model.addAttribute("error", error);
        return "common/login";
    }

    /**
     * 进入注册页面.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/register")
    public ModelAndView toRegister(HttpServletRequest request) {
        randomUUID(request);
        return new ModelAndView("common/register");
    }

    /**
     * 注册帐号.<br/>
     * 
     * @author zhanghanlin
     * @param user
     * @param request
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/doRegister", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView doRegister(User user, String UUID, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        if (!checkUUID(UUID, request)) {
            return new ModelAndView("redirect:/login");
        }
        if (user != null) {
            SimpleHash hash = new SimpleHash("md5", user.getPassword(), null, 2);
            user.setPassword(hash.toHex());
            int res = userService.add(user);
            if (res <= 0) {
                model.addObject("msg", "注册失败");
                model.setViewName("redirect:/register");
            } else {
                model.setViewName("redirect:/login");
            }
        }
        return model;
    }

    /**
     * ICON.<br/>
     * 
     * @author zhanghanlin
     * @param request
     * @param response
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/icons", method = RequestMethod.GET)
    public ModelAndView icons() {
        return new ModelAndView("/common/icons");
    }

    @RequiresPermissions("sys_monitor:monitor")
    @RequestMapping(value = "/toMonitor")
    public ModelAndView toMonitor() {
        ServerStatus status = null;
        try {
            status = ServerInfo.getServerStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("/common/monitor", "status", status);
    }

    @RequiresPermissions("sys_monitor:monitor")
    @RequestMapping(value = "/monitor")
    @ResponseBody
    public Map<String, Object> monitorInfo() {
        ServerStatus status = null;
        try {
            status = ServerInfo.getServerStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (status == null) {
            return dataMap;
        }
        String cpuUsage = status.getCpuUsage();
        long useMem = status.getUsedMem();
        long TotalMem = status.getTotalMem();
        String serverUsage = StringUtils.fromUsage(useMem, TotalMem);
        long JvmFreeMem = status.getJvmFreeMem();
        long JvmTotalMem = status.getJvmTotalMem();
        String JvmUsage = StringUtils.fromUsage(JvmTotalMem - JvmFreeMem, JvmTotalMem);
        dataMap.put("cpuUsage", cpuUsage);
        dataMap.put("serverUsage", serverUsage);
        dataMap.put("JvmUsage", JvmUsage);
        return dataMap;
    }
}