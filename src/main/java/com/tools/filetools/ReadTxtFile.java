package com.tools.filetools;

import com.tools.config.LoggerControler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by vidorh on 8/2/2016.
 */
public class ReadTxtFile {
    static final LoggerControler logger = LoggerControler.getLogger(ReadTxtFile.class);
    static String path = System.getProperty("user.dir") + File.separator + "jsondir" + File.separator;

    public static String readTxtFile(String fileName) {
        String lineTxt = null;
        StringBuffer stringBuffer = null;
        try {
            File file = new File(path + fileName);
            //判断文件是否存在
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(read);
                stringBuffer = new StringBuffer();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    stringBuffer.append(lineTxt);
//                    System.out.println(lineTxt);
                }
//                logger.info(stringBuffer);
                read.close();
            } else {
                logger.error("找不到指定的文件");
            }
        } catch (Exception e) {
            logger.error("读取文件内容出错");
            e.printStackTrace();
        }
        return String.valueOf(stringBuffer);
    }

}
