package com.demo.java.test.template;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.velocity.VelocityContext;
import org.junit.Assert;
import org.junit.Test;

import com.demo.java.dict.Status;
import com.demo.java.entity.CustomPage;
import com.demo.java.service.impl.CustomPageServiceImpl;
import com.demo.java.test.AbstractTest;
import com.demo.java.utils.file.FileConstants;
import com.demo.java.utils.file.FileUtils;
import com.demo.java.utils.file.VmUtils;

public class CustomPageTest extends AbstractTest {

    @Resource
    CustomPageServiceImpl customPageService;

    @Test
    public void testCreate() {
        CustomPage t = new CustomPage();
        t.setCreatedAt(new Date());
        t.setCreatedBy("System");
        t.setName("test template");
        t.setStatus(Status.NORMAL);
        t.setIsRefresh(0);
        t.setPath("test path");
        customPageService.save(t);
    }

    @Test
    public void testUpdate() {
        CustomPage t = customPageService.get(4L);
        t.setPath("test path");
        t.setChangedAt(new Date());
        int a = customPageService.update(t);
        Assert.assertTrue(a > 0);
    }

    @Test
    public void testList() {
        List<CustomPage> list = customPageService.getList();
        for (CustomPage t : list) {
            System.out.println(t.toJSON());
        }
    }

    @Test
    public void testPageVm() {
        String data = "Page Test";
        String fileName = FileConstants.PAGE_VM_PREFIX + String.valueOf(((new Date()).getTime() / 1000));
        FileUtils.createFile(fileName + ".vm", FileConstants.PAGE_VM_PATH, data);
        boolean res = VmUtils.vm2PageHtml(new VelocityContext(), fileName);
        Assert.assertTrue(res);
    }
}