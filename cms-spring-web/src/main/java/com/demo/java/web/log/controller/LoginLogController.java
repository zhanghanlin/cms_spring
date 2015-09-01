package com.demo.java.web.log.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.log.entity.LoginLog;
import com.demo.java.log.service.LoginLogService;
import com.demo.java.web.common.controller.AbstractController;
import com.demo.java.web.common.vo.PageVo;

@ControllerAdvice
@RequestMapping("loginLog")
public class LoginLogController extends AbstractController {

    @Resource
    LoginLogService loginLogService;

    /**
     * 跳转到列表页.<br/>
     * 
     * @author zhanghanlin
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("log:login")
    @RequestMapping
    public ModelAndView main() {
        return new ModelAndView("log/login_list");
    }

    /**
     * 用户数据列表.<br/>
     * 
     * @author zhanghanlin
     * @param curPage
     * @param pageSize
     * @return
     * @since JDK 1.7
     */
    @RequiresPermissions("log:login")
    @RequestMapping("list")
    @ResponseBody
    public PageVo<LoginLog> list(int curPage, int pageSize) {
        List<LoginLog> result = loginLogService.findListByPage(curPage, pageSize);
        int totalResults = loginLogService.getToalCount();
        return new PageVo<LoginLog>(curPage, pageSize, totalResults, result);
    }
}
