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
        String path = "https://www9.qp.ehealthinsurance.com/bose-api/session/create?scriptId=1&firstName=vrtyty&middleName=&lastName=vrtrt&zip=&phoneNumber=&dnis=8882960117&inboundNumber=8882960117&ciscoCallId=&allid=EHM39972&userRole=Medicare%20Sales&callType=&ticket=U0sKREVGQVVMVF9DTRAAELc8caJhoWSZxrvFIWRZvn%2ByNR%2FRaw9PKXyrcH8wmA%2FIx5TCSEaKDMLwysGr2nOZ4ziDhqkbddJq9k7oHaaLAV6FnIPgPYsSLUKNba8F47qm&returnURL=https://www9.qp.ehealthinsurance.com/ehi/mc/search&boUserID=medicaresales-july&email=&subAllianceId=&continueCall=Y&elg=&isPFAMember=&wantToBePFAMember=&rxSessionId=";
        Response response = ApiTools.post(path);

        String json = response.jsonPath().get("message");
        System.out.println(json);
    }

    @Test
    public void postTest02() {
        String path = "https://www9.qp.ehealthinsurance.com/bose-api/lead/agent-notes";
        String json = "{\"sessionId\":244636,\"agentNotes\":\"notepad\"}";
        Response response = ApiTools.post(path, json);
        response.body().print();
    }

    @Test
    public void postTest03() {
        String path = "https://www9.qp.ehealthinsurance.com/bose-api/enrollment/paper?sessionId=244357&planIndex=0&pageNum=6&pageName=Instructions";
        Response response = ApiTools.post(path);

    }


    @Test
    public void getTest01() {
        String path = "https://www9.qp.ehealthinsurance.com/bose-api/plan/info?cacheSlayer=1469515117442&birthday=07-18-1956&carrierFamilyName=ANTHEM+PAPER&gender=F&method=PAPER&planKey=90121100:19&planType=pdp&zip=90001";
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
