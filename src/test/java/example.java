import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class example {

    @Test
    public void func_1(){
        Response response= RestAssured.get("https://reqres.in/api/users/2");
        //System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getTime());

        Response response1=RestAssured.get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();
        System.out.println(response1.asPrettyString());

    }
    @Test
    public void func_2(){
    baseURI="https://reqres.in";
             given()
            .get("/api/users/2")
            .then()
            .statusCode(200)
            .body("data.id",equalTo(2))
            .log()
            .all();
    }
}
