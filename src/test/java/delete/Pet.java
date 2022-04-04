package delete;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import utils.PayloadUtil;


public class Pet {
    int id = 5667;
    String name = "miow";
    String status = "available";

    @Test
    public void deletePetTest(){

        RestAssured.given().accept("application/json").when()
                .delete(String.valueOf(id))
                .then().statusCode(200);


    }
    @Before

    public  void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath= "v2/pet";
        RestAssured.given().accept("application/json")
                .contentType("application/json")
                .body(PayloadUtil.getPetPayload(id,name,status))
                .when().post()
                .then().statusCode(200);
//https://petstore.swagger.io/v2/pet/{petId}
    }


}
