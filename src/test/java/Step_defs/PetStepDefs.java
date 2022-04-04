package Step_defs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.PetPojo;

public class PetStepDefs {
Response response;
    @Given("I have valid url to create post")
    public void i_have_valid_url_to_create_post() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";



    }

    @When("I send Post request to create a pet")
    public void i_send_post_request_to_create_a_pet() {
        PetPojo pet = new PetPojo();
        pet.setStatus("available");
        pet.setName("buuzz");
        pet.setId(8765);

     response = RestAssured.given().accept("application/json")
                .contentType("application/json")
                .body(pet)
                .when().post();

    }

    @Then("status code should be {int}")
    public void status_code_should_be(int expectedstatuscode) {
      int actualstatuscode = response.statusCode();
        Assert.assertEquals(expectedstatuscode,actualstatuscode);

    }

    @And("response should be in json format")
    public void response_should_be_in_json_format() {
String actualcontentType = response.contentType();
String expectedContentype= "application/json";
Assert.assertEquals(expectedContentype,actualcontentType);




    }

}
