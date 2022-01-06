package com.tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostCall {

    @Test
    public void test_1(){
        baseURI="https://reqres.in/api";
        JSONObject data=new JSONObject();
        data.put("name","chaitanya");
        data.put("job","tester");

        System.out.println(data);
        //System.out.println(data.toJSONString());
                 given()
                .body(data.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().all();
    }

}
