package com.tools.apitools;

import com.jayway.restassured.response.Response;
import com.tools.config.LoggerControler;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by vidorh on 7/28/2016.
 */
public class ApiTools {
    static LoggerControler log = LoggerControler.getLogger(ApiTools.class);

    /**
     * 带json的post请求
     *
     * @param apiPath api地址
     * @param json    请求json
     * @return api返回的Response
     */
    public static Response post(String apiPath, String json) {
//        开始发起post 请求
        Response response = given().
                contentType("application/json;charset=UTF-8").
                headers("headers1", "value1", "headers2", "values2").
                cookies("cookies1", "values1", "cookies2", "values2").
                body(json).
                when().log().all().post(apiPath.trim());
        log.info(response.statusCode());
        log.info("reponse:");
        response.getBody().prettyPrint();
        return response;
    }

    /**
     * 非json形式的post请求
     *
     * @param apiPath api地址
     * @return api返回
     */
    public static Response post(String apiPath) {
//        Cookie someCookie = new Cookie.Builder("JSESSIONID", jsessionid).setSecured(true).setComment("some comment").build();
        Response response = given().
                contentType("application/json;charset=UTF-8").
                headers("headers1", "value1", "headers2", "values2").
                cookies("cookies1", "values1", "cookies2", "values2").
                when().log().all().post(apiPath.trim());
        log.info(response.statusCode());
        log.info("reponse:");
        response.getBody().prettyPrint();
        return response;
    }

    /**
     * get 请求
     *
     * @param apiPath api路径
     * @return api的response
     */
    public static Response get(String apiPath) {
        Response response = given().
                contentType("application/json;charset=UTF-8").
                headers("headers1", "value1", "headers2", "values2").
                cookies("cookies1", "values1", "cookies2", "values2").
                when().log().all().get(apiPath.trim());
        log.info(response.statusCode());
        log.info("reponse:");
        response.getBody().prettyPrint();
        return response;
    }

    @Test
    public void testGet(){
        Response response = ApiTools.get("xxxxxxxxxx");
        log.info(response);
    }


    /**
     * 获取json中某个key值
     *
     * @param response 接口返回
     * @param jsonPath jsonpath, 例如 a.b.c   a.b[1].c  a
     * @return
     */
    public static String getJsonPathValue(Response response, String jsonPath) {
        String reponseJson = String.valueOf(response.jsonPath().get(jsonPath));
//        String jsonValue = String.valueOf(from(reponseJson).get(jsonPath));
        return reponseJson;
    }

}
