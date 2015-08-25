package com.demo.java.web.common.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.string.StringUtils;

public abstract class AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    public static String randomUUID(HttpServletRequest request) {
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        Object uuid = session.getAttribute("UUID");
        session.setAttribute("UUID", UUID.randomUUID().toString());
        return uuid == null ? "" : uuid.toString();
    }

    public static boolean checkUUID(String uuid, HttpServletRequest request) {
        String sessionUUID = randomUUID(request);
        if (StringUtils.isBlank(uuid) || !uuid.equals(sessionUUID)) {
            return false;
        }
        return true;
    }
}