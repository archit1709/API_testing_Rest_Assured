import org.testng.annotations.Test;

 import java.util.HashMap;

 import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITesting_GET_POST {
    public int id;

    @Test(priority = 3)
    void getAllUsers(){
        given()


                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .contentType("application/json; charset=utf-8")
                .log().all();

    }

    @Test(priority = 1)
    void createUser(){
       //String  id;
        HashMap data=new HashMap();
        data.put("name","testing1");
        data.put("job","tester1");

    id=  given()
                .contentType("application/json")
                .body(data)
                .when()
                    .post("https://reqres.in/api/users")
             .jsonPath().getInt("id");

              //  .then()
              // .statusCode(201)
              //  .body("name",equalTo("testing"))
             //   .log().all();
    }

    @Test(dependsOnMethods = "createUser",priority = 2)
    void updateUser(){
        HashMap data1=new HashMap();
        data1.put("name","demo");
        data1.put("job","architect");

        given()
                .body(data1)
              .when()
                .put("https://reqres.in/api/users/"+id)
                . then()
                .statusCode(200)
              //  .body("name",equalTo("demo"))
                .log().all();
    }

}
