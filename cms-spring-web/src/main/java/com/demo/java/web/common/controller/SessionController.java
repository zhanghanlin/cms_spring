package com.demo.java.web.common.controller;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.java.web.common.response.DefaultEnum;
import com.demo.java.web.common.response.ResponseContent;

@Controller
@RequestMapping("sessions")
public class SessionController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(SessionController.class);

    @Resource
    SessionDAO sessionDAO;

    @RequestMapping
    public ModelAndView toList() {
        ModelAndView model = new ModelAndView("session/list");
        model.addObject("sessions", sessionDAO.getActiveSessions());
        return model;
    }

    @RequestMapping("/{sessionId}/forceLogout")
    @ResponseBody
    public ResponseContent<Object> forceLogout(@PathVariable String sessionId) {
        try {
            Session session = sessionDAO.readSession(sessionId);
            if (session != null) {
                session.setAttribute("session.force.logout", Boolean.TRUE);
                return new ResponseContent<Object>(DefaultEnum.SUCCESS);
            }
        } catch (Exception e) {
            LOG.error("session:logout error : {}", e.getMessage(), e);
        }
        return new ResponseContent<Object>(DefaultEnum.ERROR);
    }
}