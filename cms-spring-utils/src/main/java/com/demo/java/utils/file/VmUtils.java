package com.demo.java.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.Constants;

public class VmUtils {

    static final Logger LOG = LoggerFactory.getLogger(VmUtils.class);

    public static boolean vm2Html(VelocityContext context, String path, String name, String vmPath, String vmName) {
        FileOutputStream fos = null;
        BufferedWriter writer = null;
        try {
            Properties p = new Properties();
            p.clear();
            p.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, vmPath);
            p.setProperty(VelocityEngine.INPUT_ENCODING, Constants.ENCODING);
            p.setProperty(VelocityEngine.OUTPUT_ENCODING, Constants.ENCODING);
            p.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogSystem");

            VelocityEngine ve = new VelocityEngine();
            ve.init(p);
            Velocity.init();

            Template template = ve.getTemplate(vmName + ".vm", Constants.ENCODING);
            FileUtils.forceMkdir(new File(path));
            fos = new FileOutputStream(path + "/" + name + ".html");
            writer = new BufferedWriter(new OutputStreamWriter(fos, Constants.ENCODING));
            if (context == null) {
                context = new VelocityContext();
            }
            template.merge(context, writer);
            return true;
        } catch (Exception e) {
            LOG.error("pagePath:{},vmName:{},error:{}", path, vmName, e.getMessage(), e);
            return false;
        } finally {
            try {
                writer.flush();
                writer.close();
                fos.close();
            } catch (IOException e) {
                LOG.error("finally close error:{}", e.getMessage(), e);
            }
        }
    }

    public static boolean vm2PageHtml(VelocityContext context, String name) {
        FileUtils.createDir(FileConstants.PAGE_HTML_PATH);
        return vm2Html(context, FileConstants.PAGE_HTML_PATH, name, FileConstants.PAGE_VM_PATH, name);
    }

    public static boolean vm2ModuleHtml(VelocityContext context, String name) {
        FileUtils.createDir(FileConstants.MODULE_HTML_PATH);
        return vm2Html(context, FileConstants.MODULE_HTML_PATH, name, FileConstants.MODULE_VM_PATH, name);
    }
}
