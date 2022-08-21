package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class CovidStatistics {

    @Test
    public void test1() {
        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get("https://corona.lmao.ninja/v2/all")// -->  get url
                .then().statusCode(200).extract().response();

        Map<String, Object> deserializeResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        //  Assert.assertEquals(227,deserializeResponse.get("affectedCountries"));
        int affectedcountries = (int) deserializeResponse.get("affectedCountries");
        Assert.assertEquals(230, affectedcountries);

        System.out.println(deserializeResponse);


    }

    @Test
    public void test2() {
        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get("https://cat-fact.herokuapp.com/facts")// -->  get url
                .then().statusCode(200).extract().response();
        List<Map<String, Object>> catfactlist = response.as(new TypeRef<List<Map<String, Object>>>() {
        });


        for (int i = 0; i < catfactlist.size(); i++) {
            Map<String, Object> catfactMAP = catfactlist.get(i);
            catfactMAP.get("text");
            System.out.println(catfactMAP.get("text"));

        }


    }

    @Test
    public void test3() {
        //get the last last key value method
        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get("https://cat-fact.herokuapp.com/facts")// -->  get url
                .then().statusCode(200).extract().response();
        List<Map<String, Object>> catfactlist = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        Map<String, Object> lastfact = catfactlist.get(catfactlist.size() - 1);
        String expectedlastfact = "Cats are the most popular pet in the United States: There are 88 million pet cats and 74 million dogs.";
        String actuallastfact = lastfact.get("text").toString();
        Assert.assertEquals(expectedlastfact, actuallastfact);

    }
@Test


    public void test4() {
        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get("https://swapi.dev/api/people")// -->  get url
                .then().statusCode(200).extract().response();

 Map<String, Object> parseresponses = response.as(new TypeRef<Map<String, Object>>() {});
 List<Map<String,Object>> listofcharacter =
         (List<Map<String, Object>>) parseresponses.get("results");


int count =0;
for(int i =0; i < listofcharacter.size();i++){
    Map<String,Object>charmap = listofcharacter.get(i);


    if(charmap.get("gender").equals("female")){
       ++count;
    }
}
   Assert.assertTrue(count > 0);

    System.out.println(count);



    }

    /**
     * GET https://swapi.dev/api/people
     * Validate we have more than 0 female character in the response
     * on page 1
     */

    @Test
    public void femaleCountTest() {
        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get("https://swapi.dev/api/people")
                .then().statusCode(200).extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        List<Map<String, Object>> listOfCharacters =
                (List<Map<String, Object>>) parsedResponse.get("results");

        int count = 0;
        for (int i = 0; i < listOfCharacters.size(); i++) {
            //getting every character
            Map<String, Object> charMap = listOfCharacters.get(i);
            //getting gender of every character
            if (charMap.get("gender").equals("female")) {
                ++count;
            }
        }

        Assert.assertTrue(count > 0);
        System.out.println(count);

    }
}



