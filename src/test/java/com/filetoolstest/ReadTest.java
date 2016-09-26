package com.filetoolstest;

import com.tools.filetools.ReadTxtFile;

import org.testng.annotations.Test;

import static com.jayway.restassured.path.json.JsonPath.from;

/**
 * Created by vidorh on 8/2/2016.
 */
public class ReadTest {
    @Test
    public void testRead() {

        String text = ReadTxtFile.readTxtFile("requestTest.json");
        System.out.print(text);
    }

    @Test
    public void testJson(){
        String json = ReadTxtFile.readTxtFile("reponseTest.json");
        String a = from(json).get("data.application.context_data.enter_when");
        System.out.println(a);
    }
}
