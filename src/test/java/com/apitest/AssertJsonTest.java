package com.apitest;

import net.javacrumbs.jsonunit.JsonAssert;
import org.testng.annotations.Test;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

/**
 * Created by vidorh on 8/3/2016.
 */
public class AssertJsonTest {

    @Test
    public void assertJsonTest(){
        assertJsonEquals("{\"test\":1}", "{\n\"test\": 12\n}");
    }

    @Test
    public void assertJsonTest01(){
        String json1 ="{\n" +
                "  \"array\": [\n" +
                "    1,\n" +
                "    2,\n" +
                "    3\n" +
                "  ],\n" +
                "  \"boolean\": true,\n" +
                "  \"null\": null,\n" +
                "  \"number\": 123,\n" +
                "  \"object\": {\n" +
                "    \"a\": \"b\",\n" +
                "    \"c\": \"d\",\n" +
                "    \"e\": \"f\"\n" +
                "  },\n" +
                "  \"string\": \"Hello World\"\n" +
                "}";

        String json2 ="{\n" +
                "    \"array\": [\n" +
                "        1,\n" +
                "        2,\n" +
                "        3\n" +
                "    ],\n" +
                "    \"boolean\": true,\n" +
                "    \"null\": null,\n" +
                "    \"number\": 123,\n" +
                "    \"object\": {\n" +
                "        \"a\": \"b\",\n" +
                "        \"c\": \"f\",\n" +
                "        \"e\": \"f\"\n" +
                "    },\n" +
                "    \"string\": \"Hello World\"\n" +
                "}";
        JsonAssert.assertJsonPartEquals(json1,json2,"object.c");
    }


}
