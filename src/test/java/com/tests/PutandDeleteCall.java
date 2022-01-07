package com.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PutandDeleteCall extends BaseTest {

    @Test
    public void updateSingleUser() {
        int userId = 2;
        JSONObject data = new JSONObject();
        data.put("name", "xyz");
        data.put("job", "abc");
        Response response = RestAssured.given().body(data.toJSONString()).contentType(ContentType.JSON)
                .pathParam("id", userId).when().put("/api/users/{id}").then().extract().response();
        JsonPath jsonData = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonData.getString("name"), "xyz");

    }

    @Test
    public void deleteSingleUser() {
        int userId = 2;
        Response response = RestAssured.given().pathParam("id", userId).delete("/api/users/{id}");
        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
