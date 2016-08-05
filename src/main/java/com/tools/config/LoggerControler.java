package com.tools.config;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by vidorh on 7/28/2016.
 */
public class LoggerControler {
    private static Logger logger = null;
    private static LoggerControler logg = null;

    public static LoggerControler getLogger(Class<?> T) {
        if (logger == null) {
//            实例化一个 Properties 类，处理log4j.Properties文件
            Properties props = new Properties();
            try {
//                获取log4j配置文件路径
                String envPaht = System.getProperty("user.dir") +
                        File.separator + "config" + File.separator + "log4j.properties";
                InputStream is = new FileInputStream(envPaht);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            log4j 的PropertyConfigurator 类的configure方法输入数据流
            PropertyConfigurator.configure(props);
            logger = Logger.getLogger(T);
            logg = new LoggerControler();
        }
        return logg;
    }

    /**
     * 重写logger方法
     */
    public void info(Object msg) {
        logger.info(msg);
    }

    public void debug(Object msg) {
        logger.debug(msg);
    }

    public void warn(Object msg) {
        logger.warn(msg);
    }

    public void error(Object msg) {
        logger.error(msg);
    }
}
