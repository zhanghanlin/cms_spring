package com.demo.java.utils.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserUtils {

    public static String getUserName() {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.getPrincipal().toString();
    }
}
