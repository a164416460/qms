package com.jrs_qms_llh.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 黄浩文
 */
public class PropertiesPlaceHolder extends Properties {
    private static final long serialVersionUID = 1L;

    public PropertiesPlaceHolder(String path) {
        try (
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(path);
        ) {
            //加载属性文件
            this.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
