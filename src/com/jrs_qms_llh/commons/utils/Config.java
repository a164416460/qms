package com.jrs_qms_llh.commons.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    private Properties properties;

    public Config(String path) {
        properties = new Properties();
        try (FileInputStream in = new FileInputStream(path)) {
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
