package com.demo.java.test.user;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.test.AbstractTest;
import com.demo.java.user.entity.User;
import com.demo.java.user.service.UserService;

public class UserTest extends AbstractTest {

    final static Logger LOG = LoggerFactory.getLogger(UserTest.class);

    @Resource
    UserService userService;

    @Test
    public void testSearch() {
        List<User> list = userService.pageList(0, 10);
        LOG.info("search list : {}", list.size());
    }
}
