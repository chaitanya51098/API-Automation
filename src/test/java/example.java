import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class example {

    @Test
    public void func_1(){
        Response response=get("https://reqres.in/api/users/2");
        //response.getStatusCode();
        //System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(),200);
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getTime());
    }
    @Test
    public void func_2(){
    baseURI="https://reqres.in";
    given().get("/api/users/2").
            then().
            statusCode(200).
            body("data.id",equalTo(2))
            .log().all();
    }
}
