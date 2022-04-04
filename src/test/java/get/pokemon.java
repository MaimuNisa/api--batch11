package get;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PokemonPojo;

import java.util.List;
import java.util.Map;

public class pokemon {

    @Before
    public void setup() {


        RestAssured.baseURI = "https://pokeapi.co";
        RestAssured.basePath = "api/v2/pokemon";

    }

    @Test
    public void pokemontest() {

        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get()// -->  get url
                .then().statusCode(200).extract().response();
        PokemonPojo deserializedresponse = response.as(PokemonPojo.class);
        Assert.assertEquals(1126, deserializedresponse.getCount());

    }


    @Test
    public void pokemontest2() {
        Response response = RestAssured.given().header("Accept", "application/json")
                .when()
                .get()// -->  get url
                .then().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
        String nexturl = jsonPath.getString("next");
        System.out.println(nexturl);


        String pokemonname1 = jsonPath.getString("results[0].name");
        //System.out.println(pokemonname1);
        List<Map<String, String>> resultslist = jsonPath.getList("results");


        for (Map<String, String> pokemon : resultslist) {
            System.out.println(pokemon.get("name"));
        }


    }
@Test

    public void pokemontest3() {

      Response response =  RestAssured.given().header("Accept", "application/json").log().all()
                .when()
                .get()// -->  get url
                .then().statusCode(200).body("count", Matchers.equalTo(1126)).and()
                .body("next", Matchers.is("https://pokeapi.co/api/v2/pokemon?offset=20&limit=20"))
                .log().body().extract().response();// log mean print out


    }
}