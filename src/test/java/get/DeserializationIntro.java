package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DeserializationIntro {

    @Test
    public void testPet(){
        // please send me back response in json format
        //output the expression to response of data type / object
     Response response =  RestAssured.given().header("Accept","application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/775")// -->  get url
                .then().statusCode(200).extract().response();
       //THIS LINE IS DO DESERIALIZATION
        //CREATING ANONYMOUS CLASS {}
        Map<String,Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {});
        System.out.println(deserializeResponse.values());
      Assert.assertEquals(775,deserializeResponse.get("id"));

    }

}
