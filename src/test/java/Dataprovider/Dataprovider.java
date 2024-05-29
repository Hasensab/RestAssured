package Dataprovider;

import org.testng.annotations.Test;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Dataprovider{
	RequestSpecification req;
	Response res;
	JsonPath path;
	JSONObject obj;
	String expN,expJ;
	
	@DataProvider(name="CreateUser")
	public Object[][] dp() {
		Object[][] data=new Object[2][2];
		data[0][0]="Hasen";
		data[0][1]="software";
		
		data[1][0]="Alima";
		data[1][1]="Bussiness";
		return data;
		
	}
	@Given("user should be on reqres url")
	public void user_should_be_on_reqres_url() {
		RestAssured.baseURI="https://reqres.in/";
		req=RestAssured.given();
		System.out.println("Given Step");
	    
	}
	
	@When("user should enter {string} and {string}")
	
	@Test(dataProvider ="CreateUser")
	public void user_should_enter_and(String n, String j) {
		obj=new JSONObject();
		 req=RestAssured.given();
		obj.put("name",n);
		obj.put("job",j);
		
		req.headers("Content-Type","application/json");
		System.out.println(obj);
		System.out.println("When step");
	   
	}

	@And("hit on users api")
	public void hit_on_users_api() {
		res=req.body(obj.toJSONString()).post("api/users");
		System.out.println("And step");
	    
	}

	@Then("add user to list")
	public void add_user_to_list() {
		System.out.println(res.asPrettyString());
		path=res.jsonPath();
		/*String job1=path.getString("job");
		System.out.println(job1);
		Assert.assertEquals(job1,expJ);*/
	    
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
  /*@BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in/";
  }

  @AfterTest
  public void deallocateMemory() {
	  req=null;
	  res=null;
  }*/

}
