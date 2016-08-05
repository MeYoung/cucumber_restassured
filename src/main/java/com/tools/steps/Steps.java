package com.tools.steps;

import com.jayway.restassured.response.Response;
import com.tools.apitools.ApiTools;
import com.tools.apitools.MyAssert;
import com.tools.filetools.ReadTxtFile;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

/**
 * Created by vidorh on 8/1/2016.
 * <p>
 * Steps 集合
 */
public class Steps {

    Response response = null;

    @When("^I send a GET request to \"(.*?)\"$")
    public void getRequest(String path) {
        response = ApiTools.get(path);
    }

    @When("^I send a POST request to \"(.*?)\"$")
    public void postRequest(String apiPath) throws Throwable {
        response = ApiTools.post(apiPath);
    }

    @When("^I send a POST request to \"(.*?)\" and request json:$")
    public void postRequestWithJson(String apiPath, String json) {
        response = ApiTools.post(apiPath, json);
    }

    @When("^I use a \"(.*?)\" file to send a POST request to \"(.*?)\"$")
    public void postRequestWihtFile(String fileName, String path) {
        String json = ReadTxtFile.readTxtFile(fileName);
        response = ApiTools.post(path, json);
    }

    @Then("^the JSON response equals$")
    public void assertResponseJson(String expected) {
        String responseJson = response.body().asString();
        assertJsonEquals(responseJson, expected);
    }

    @Then("^the JSON response equals json file \"(.*?)\"$")
    public void theJSONResponseEqualsJsonFile(String fileName) {
        String responseJson = response.body().asString();
        String fileJson = ReadTxtFile.readTxtFile(fileName);
        assertJsonEquals(responseJson, fileJson);
    }

    @Then("^the response status should be \"(\\d{3})\"$")
    public void assertStatusCode(int statusCode) {
        Object jsonResponse = response.getStatusCode();
        MyAssert.assertEquals(jsonResponse, statusCode);
    }

    @Then("^the JSON response \"(.*?)\" equals \"(.*?)\"$")
    public void assertEquals(String str, String expected) {
        String jsonValue = ApiTools.getJsonPathValue(response, str);
        MyAssert.assertEquals(jsonValue, expected);
    }

    @Then("^the JSON response \"(.*?)\" type should be \"(.*?)\"$")
    public void assertMatch(String str, String match) {
        String jsonValue = ApiTools.getJsonPathValue(response, str);
        MyAssert.assertMatch(jsonValue, match);
    }

    @Then("^the JSON response \"(.*?)\" should be not null$")
    public void assertNotNull(String str) {
        String jsonValue = ApiTools.getJsonPathValue(response, str);
        MyAssert.assertNotNull(jsonValue);
    }

    @Then("^the JSON response \"(.*?)\" start with \"(.*?)\"$")
    public void assertStartWith(String str, String start) {
        String jsonValue = ApiTools.getJsonPathValue(response, str);
        MyAssert.assertStartWith(jsonValue, start);
    }

    @Then("^the JSON response \"(.*?)\" end with \"(.*?)\"$")
    public void assertEndWith(String str, String end) {
        String jsonValue = ApiTools.getJsonPathValue(response, str);
        MyAssert.assertEndWith(jsonValue, end);
    }

    @Then("^the JSON response \"(.*?)\" include \"(.*?)\"$")
    public void assertInclude(String str, String include) {
        String jsonValue = ApiTools.getJsonPathValue(response, str);
        MyAssert.assertInclude(jsonValue, include);
    }

}
