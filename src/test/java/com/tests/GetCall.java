package com.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItem;

public class GetCall extends BaseTest {
    @Test
    public void getSingleUser() {
        int userId = 2;
        Response response = RestAssured.given().pathParam("id", userId).get("/api/users/{id}")
                .then().extract().response();
        JsonPath jsonData = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonData.getInt("data.id"), 2);
        Assert.assertEquals(jsonData.get("data.last_name"), "Weaver");
    }

    @Test
    public void getAllUsersOfPage() {
        int pageNumber = 2;
        Response response = RestAssured.given().queryParam("page", pageNumber).get("/api/users")
                .then().extract().response();
        JsonPath jsonData = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonData.getInt("page"), 2);
        Assert.assertEquals(jsonData.getInt("data[0].id"), 7);
        Assert.assertEquals(jsonData.get("data[0].email"), "michael.lawson@reqres.in");
        Assert.assertTrue(jsonData.getString("data.email").contains("@reqres.in"));
        Assert.assertNotNull(jsonData.get("data.first_name"));
    }

    @Test
    public void singleUserNotFound() {
        int unknownId = 23;
        Response response = RestAssured.given().pathParam("id", unknownId).get("/api/users/{id}")
                .then().extract().response();
        Assert.assertEquals(response.getStatusCode(), 404);
        Assert.assertEquals(response.asString(), "{}");
    }

    @Test
    public void listOfResources() {
        Response response = get("/api/unknown")
                .then().extract().response();
        JsonPath jsonData = response.jsonPath();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(jsonData.get("data[1].name"), "fuchsia rose");
        Assert.assertEquals(jsonData.getInt("data[0].id"), 1);
        Assert.assertEquals(jsonData.getString("data[1].color"), "#C74375");
    }
}