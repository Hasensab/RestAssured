package StepDefinition;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserListTest {
	RequestSpecification req;
	Response res;
	@Given("user is on reqres URL")
	public void user_is_on_reqres_url() {
		RestAssured.baseURI="https://reqres.in/";
		req=RestAssured.given();
		System.out.println("Given Step");
	}

	@When("user hits the users API")
	public void user_hits_the_users_api() {
		 res=req.get("/api/users?page=2");
		 System.out.println("When step");
	}

	@Then("All the user details displayed")
	public void all_the_user_details_displayed() {
		String result=res.asPrettyString();
		 JsonPath path=res.jsonPath();
		 String id=path.getString("data[1].id");
		 Assert.assertEquals(id,"8","id mismatched");
		 System.out.println("Then step");
	   
	}



}
