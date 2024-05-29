package Steps;

import java.util.List;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddUserTest {
	RequestSpecification req;
	Response res;
	JsonPath path;
	JSONObject obj;
	String expN,expJ;
	//-------Doing for First senario(Adding  user)--------------
		/*Created By:Hasensab
		 * Reviewed By:Deepali
		 * Description:This step Adding users to the list
		 */
	
	@Given("user is on reqres url")
	public void user_is_on_reqres_url() {
		RestAssured.baseURI="https://reqres.in/";
		req=RestAssured.given();
		System.out.println("Given Step");
	    
	}

	@When("user enters the {string} and {string}")
	public void user_enters_the_and(String name, String job) {
		obj=new JSONObject();
		obj.put("name",name);
		obj.put("job",job);
		expN=name;
		expJ=job;
		req.headers("Content-Type","application/json");
		System.out.println(obj);
		System.out.println("When step");
		
	    
	}

	@And("user hit the users API")
	public void user_hit_the_users_api() {
		res=req.body(obj.toJSONString()).post("/api/users");
		System.out.println("And step");
	    
	}

	@Then("users are added to list")
	public void users_are_added_to_list() {
		System.out.println(res.asPrettyString());
		path=res.jsonPath();
		String job1=path.getString("job");
		System.out.println(job1);
		Assert.assertEquals(job1,expJ);
		
	    
	}
	
	
//-------Doing for Second senario(updating user)--------------
	/*Created By:Hasensab
	 * Reviewed By:Deepali
	 * Description:This step updating the users
	 */
	@When("enters name and job")
	public void enters_name_and_job(DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		
		List<List<String>> udata=dataTable.asLists(String.class);
		String name=udata.get(0).get(0);
		String job=udata.get(0).get(1);
		
		
		//List<String> cell=dataTable.asList(String.class);
		//String name=cell.get(0);
		obj=new JSONObject();
		obj.put("name",name);
		//obj.put("job",job);
		expN=name;
		//expJ=job;
		req.headers("Content-Type","application/json");
		System.out.println(obj);
		System.out.println("When step");
		
	  
	}

	@And("user hits the API")
	public void user_hits_the_api() {
		res=req.body(obj.toJSONString()).post("api/users");
		System.out.println("And step");
	    
	}

	@Then("user data is updated")
	public void user_data_is_updated() {
		System.out.println(res.asPrettyString());
		path=res.jsonPath();
		String job1=path.getString("job");
		System.out.println(job1);
		Assert.assertEquals(job1,expJ);
		
	}





}
