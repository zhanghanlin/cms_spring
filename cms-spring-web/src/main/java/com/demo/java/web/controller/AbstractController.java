package com.demo.java.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.string.StringUtils;

public abstract class AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    public static String randomUUID(HttpServletRequest request) {
        Object uuid = request.getSession().getAttribute("UUID");
        request.getSession().setAttribute("UUID", UUID.randomUUID().toString());
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