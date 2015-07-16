package com.demo.java.web.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.java.web.controller.AbstractController;

@Controller
@RequestMapping("/login")
public class LoginController extends AbstractController {

    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
}
