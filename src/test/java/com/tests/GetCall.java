package com.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItem;

public class GetCall {
    @Test
    public void getSingleUser(){
        Response response= RestAssured.get("https://reqres.in/api/users/2")
                .then().extract().response();
        JsonPath jsonData=response.jsonPath();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(jsonData.getInt("data.id"),2);
        Assert.assertEquals(jsonData.get("data.last_name"),"Weaver");
    }
    @Test
    public void getAllUsersOfPage(){
        Response response=RestAssured.get("https://reqres.in/api/users?page=2")
                .then().extract().response();
        JsonPath jsonData=response.jsonPath();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(jsonData.getInt("page"),2);
        Assert.assertEquals(jsonData.getInt("data[0].id"),7);
        Assert.assertEquals(jsonData.get("data[0].email"),"michael.lawson@reqres.in");
    }
    @Test
    public void singleUserNotFound(){
        Response response=RestAssured.get("https://reqres.in/api/users/23")
                .then().extract().response();
        Assert.assertEquals(response.getStatusCode(),404);
        Assert.assertNull(response.body().asString());
    }
    @Test
    public void listOfResources(){
        Response response=get("https://reqres.in/api/unknown")
                .then().extract().response();
        JsonPath jsonData=response.jsonPath();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(jsonData.get("data[1].name"),"fuchsia rose");
        Assert.assertEquals(jsonData.getInt("data[0].id"),1);
        Assert.assertEquals(jsonData.getString("data[1].color"),"#C74375");
    }
}