package com.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostCall extends BaseTest {

    @Test
    public void createSingleUser() {
        JSONObject userData = new JSONObject();
        userData.put("name", "abc");
        userData.put("job", "zxc");
        Response response = RestAssured.given().body(userData.toJSONString()).contentType(ContentType.JSON)
                .when().post("/api/users").then().extract().response();
        JsonPath jsonData = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(jsonData.get("name"), "abc");
    }

}
