package com.demo.java.test.user;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
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
    public void testRegister() {
        User user = new User();
        user.setUserName("test");
        user.setPhone("13111111111");
        user.setEmail("test@test.com");
        user.setPassword("test");
        SimpleHash hash = new SimpleHash("md5", user.getPassword(), null, 2);
        user.setPassword(hash.toHex());
        userService.save(user);
    }
}