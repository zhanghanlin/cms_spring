package com.demo.java.test.user;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.test.AbstractTest;
import com.demo.java.user.entity.UserVo;
import com.demo.java.user.service.UserService;
import com.demo.java.web.common.vo.PageVo;

public class UserTest extends AbstractTest {

    final static Logger LOG = LoggerFactory.getLogger(UserTest.class);

    @Resource
    UserService userService;

    @Test
    public void testPage() {
        int curPage = 1;
        int pageSize = 1;
        List<UserVo> result = userService.findListByPage(curPage, pageSize);
        int totalResults = userService.getToalCount();
        PageVo<UserVo> page = new PageVo<UserVo>(curPage, pageSize, totalResults, result);
        LOG.info(page.toJSON());
    }
}