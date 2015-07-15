package com.demo.java.test.template;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.velocity.VelocityContext;
import org.junit.Assert;
import org.junit.Test;

import com.demo.java.dict.Status;
import com.demo.java.entity.Module;
import com.demo.java.service.impl.ModuleServiceImpl;
import com.demo.java.test.AbstractTest;
import com.demo.java.utils.file.FileConstants;
import com.demo.java.utils.file.FileUtils;
import com.demo.java.utils.file.VmUtils;

public class ModuleTest extends AbstractTest {

    @Resource
    ModuleServiceImpl moduleService;

    @Test
    public void testCreate() {
        Module t = new Module();
        t.setCreatedAt(new Date());
        t.setCreatedBy("System");
        t.setName("test template");
        t.setStatus(Status.NORMAL);
        t.setIsRefresh(0);
        t.setType(0);
        moduleService.save(t);
    }

    @Test
    public void testList() {
        List<Module> list = moduleService.getList();
        for (Module t : list) {
            System.out.println(t.toJSON());
        }
    }

    @Test
    public void testVm() {
        String data = "#set ($i=0) #set ($list = [1,2,3,4,5]) #foreach($info in $list) 序号:$i #set($i=$i+1) #end";
        String fileName = FileConstants.MODULE_VM_PREFIX + String.valueOf(((new Date()).getTime() / 1000));
        FileUtils.createFile(fileName + ".vm", FileConstants.MODULE_VM_PATH, data);
        boolean res = VmUtils.vm2ModuleHtml(new VelocityContext(), fileName);
        Assert.assertTrue(res);
    }
}