package com.demo.java.test.role;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.role.dao.RoleMenuDao;
import com.demo.java.test.AbstractTest;

public class RoleTest extends AbstractTest {

    final static Logger LOG = LoggerFactory.getLogger(RoleTest.class);

    @Resource
    RoleMenuDao roleMenuDao;

    @Test
    public void testAdd() {
    }
}