package com.demo.java.web.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);

    public static String randomUUID(HttpServletRequest request) {
        Object uuid = request.getSession().getAttribute("UUID");
        request.getSession().setAttribute("UUID", UUID.randomUUID().toString());
        return uuid == null ? "" : uuid.toString();
    }
}