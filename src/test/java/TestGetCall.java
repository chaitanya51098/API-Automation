import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;

public class TestGetCall {

    @Test
    public void GetCall1(){
        Response response= RestAssured.get("https://reqres.in/api/users/2")
                .then().extract().response();
        //System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asPrettyString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getTime());
        JsonPath jsonData=response.jsonPath();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(jsonData.getInt("data.id"),2);
        Assert.assertEquals(jsonData.get("data.last_name"),"Weaver");
    }
    @Test
    public void GetCall2(){

        baseURI="https://reqres.in";
        Response response=RestAssured.get("https://reqres.in/api/users?page=2")
                .then().extract().response();
        JsonPath jsonData=response.jsonPath();

                 given()
                .get("/api/users?page=2")
                .then()
                .body("data.email",hasItem("michael.lawson@reqres.in"))
                .body("data.id",hasItem(8));

             System.out.println((ArrayList) jsonData.get("data.id"));
             System.out.println((ArrayList) jsonData.get("data.email"));
             Assert.assertEquals(jsonData.getInt("page"),2);
             Assert.assertEquals(jsonData.getInt("data[0].id"),7);

    }
    @Test
    public void GetCall3(){
        baseURI="https://reqres.in/";
        given()
                .get("api/users/23")
                .then()
                .statusCode(404);
        Assert.assertEquals(RestAssured.get("https://reqres.in/api/users/23").getStatusCode(),404);
    }
    @Test
    public void GetCall4(){
        Response response=get("https://reqres.in/api/unknown");
        JsonPath jsonData=response.jsonPath();
        Assert.assertEquals(jsonData.get("data[1].name"),"fuchsia rose");
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(jsonData.getInt("data[0].id"),1);
                 given()
                .get("https://reqres.in/api/unknown")
                .then()
                .body("data.year",hasItem(2005))
                .body("data.color",hasItem("#E2583E"));
    }

}
