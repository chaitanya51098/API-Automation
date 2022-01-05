import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestPutandDeleteCall {

    @Test
    public void test_1(){
        baseURI="https://reqres.in";
        JSONObject data=new JSONObject();
        data.put("name","xyz");
        data.put("job","abc");
        given()
        .body(data.toJSONString())
        .when()
        .put("/api/users/2")
        .then()
        .statusCode(200);
    }
    @Test
    public void test_2(){
        baseURI="https://reqres.in";
                 when()
                .delete("/api/users/2")
                .then()
                .statusCode(204);
    }
}
