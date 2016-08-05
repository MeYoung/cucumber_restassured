package com.bose.apitoolstest;

import com.tools.config.LoggerControler;
import org.testng.annotations.Test;

/**
 * Created by vidorh on 7/28/2016.
 */
public class LogTest {
    final static LoggerControler log = LoggerControler.getLogger(LogTest.class);

    @Test
    public void testLog(){
        log.info("this is info");
        log.debug("this is info");

        log.error("this is info");
        log.warn("this is info");

    }
}
