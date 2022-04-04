package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.StarWarsPojo;
import pojo.StarwarsCharacterPojo;

import java.util.List;
import java.util.Map;

public class Starwars {
    @Test
    public void deserializeWithPojo() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();


        StarWarsPojo deserializedRes = response.as(StarWarsPojo.class);
        Assert.assertEquals(82, deserializedRes.getCount());

//      List<Map<String,Object>> results = deserializedRes.getResults();
//
//      for (Map<String,Object>map : results){
//          System.out.println(map);
//      }
//    }
     List<StarwarsCharacterPojo> results = deserializedRes.getResults();

     for (StarwarsCharacterPojo a : results){
         System.out.println(a.getName()+ "---- " + a.getBirth_year());
     }


    }

}
