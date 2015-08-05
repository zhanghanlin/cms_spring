package com.demo.java.web.role.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.java.web.common.controller.AbstractController;

@Controller
@RequestMapping("role")
public class RoleController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

}