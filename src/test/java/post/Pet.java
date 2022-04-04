package post;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayloadUtil;

public class Pet {

String petName= "doggie";
int  petId = 4567;
String petStatus = "available";

    @Test

    public void createPetTest(){

        Response response =RestAssured.given()
                .header("Accept","application/json")
                .header("Content-Type","application/json")
                .body(PayloadUtil.getPetPayload(petId,petName,petStatus))
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then().statusCode(200).extract().response();

               PetPojo parseResponse = response.as(PetPojo.class);
        Assert.assertEquals(petId,parseResponse.getId());
        Assert.assertEquals(petName,parseResponse.getName());
        Assert.assertEquals(petStatus,parseResponse.getStatus());

        RestAssured.given()
                .header("Accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/4567")
                .then().statusCode(200)
                .and()
                .body("id", Matchers.is(petId))
                .and()
                .body("name",Matchers.is(petName))
                .body("status",Matchers.is(petStatus))
                .body("category.id",Matchers.is(0));


    }


}
