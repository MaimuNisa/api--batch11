package put;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PetPojo;
import post.Slack;

import java.util.Map;

public class Pet {


    @Before
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.basePath="v2/pet";
    }
    @Test
    public void updatePetTest(){
        PetPojo pet = new PetPojo();
        pet.setName("pet from selenium");
        pet.setId(4567);
        pet.setStatus("sleep");

     Response response = RestAssured.given()
                .accept(Slack.APPLICATION_JSON )
                .contentType(Slack.APPLICATION_JSON)
                .body(pet)
                .when().put().then().statusCode(200).extract().response();

        Map<String,Object>deserializedResp = response.as(new TypeRef<Map<String, Object>>() {
        });

        String name = String.valueOf(deserializedResp.get("name"));
      //  String name1 = (String) deserializedResp.get("name").toString();

        Assert.assertEquals("pet from selenium",name);

        int id = (int) deserializedResp.get("id");

        Assert.assertEquals(4567,id);

    }
}
