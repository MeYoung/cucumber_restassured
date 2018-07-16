package com.apitest;

import com.tools.apitools.ApiTools;

import com.jayway.restassured.response.Response;
import com.tools.apitools.MyAssert;
import org.testng.annotations.Test;

/**
 * Created by vidorh on 7/28/2016.
 */
public class ApiTest {


    @Test
    public void postTest01() {
        String path = "xxxxxx";
        Response response = ApiTools.post(path);

        String json = response.jsonPath().get("message");
        System.out.println(json);
    }

    @Test
    public void postTest02() {
        String path = "xxxxx";
        String json = "{\"sessionId\":244636,\"agentNotes\":\"notepad\"}";
        Response response = ApiTools.post(path, json);
        response.body().print();
    }

    @Test
    public void postTest03() {
        String path = "xxxxxxx";
        Response response = ApiTools.post(path);

    }


    @Test
    public void getTest01() {
        String path = "xxxxxx";
        Response response = ApiTools.get(path);
        response.getBody().jsonPath().get("ehiCarrierId").equals(90121100);
    }

    @Test
    public void assertTest() {
        String a = "{\n" +
                "\"result\": \"success\"\n" +
                "}";
        String b = "{\n" +
                "\"result\": \"success\"\n" +
                "}";

        MyAssert.assertEquals(a, b);
    }

}
