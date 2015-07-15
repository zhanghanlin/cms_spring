package com.demo.java.utils.file;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.Constants;

public class FileUtils extends org.apache.commons.io.FileUtils {

    static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);

    public static void createFile(String name, String path, String data) {
        File file = new File(path + "/" + name);
        LOG.info("createFile path:{},name:{}", path, name);
        try {
            writeStringToFile(file, data, Constants.ENCODING);
        } catch (IOException e) {
            LOG.error("createFile IOException Error:{}", e.getMessage(), e);
        }
    }

    public static void createDir(String... path) {
        if ((path == null) || (path.length == 0)) {
            LOG.warn("createDir path is null");
        }
        for (String str : path) {
            try {
                File file = new File(str);
                forceMkdir(file);
            } catch (IOException e) {
                LOG.error("createDir IOException Error:{}", e.getMessage(), e);
            }
        }
    }
}
