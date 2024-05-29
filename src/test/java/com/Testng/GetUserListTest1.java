package com.Testng;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import org.testng.annotations.BeforeTest;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;

public class GetUserListTest1 {
	RequestSpecification req;
	Response res;
  @BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in/";
  }
  @Test(priority = 0)
  public void getData() {
	  req=RestAssured.given();
	  res=req.get("/api/users?page=2");
	  System.out.println(res.asPrettyString());
	  //doing assertions
	 Assert.assertEquals(res.getStatusCode(),200);
	 JsonPath data=res.jsonPath();
	 String page=data.getString("page");
	 System.out.println(page);
	 String per_page=data.getString("per_page");
	 System.out.println(per_page);
	 String total=data.getString("total");
	 System.out.println(total);
	 String total_pages=data.getString("total_pages");
	 System.out.println(total_pages);
	 String d=data.getString("data");
	 System.out.println(d);
	 String id=data.getString("data[0].id");
	 System.out.println(d);
	 Assert.assertEquals(id,"7");
	 String email=data.getString("data[0].email");
	 System.out.println(email);
	 Assert.assertEquals(email,"michael.lawson@reqres.in");
	 
  }
  @Test(priority = 1,dataProvider="CreateUser",dataProviderClass=DataProviderTest.class)
  public void addData(String name,String job){
	  req=RestAssured.given();
	  JSONObject obj=new JSONObject();
	  	obj.put("name",name);
		obj.put("job",job);
		req.headers("Content-Type","application/json");
		res=req.body(obj.toJSONString()).post("/api/users");
		System.out.println(res.asPrettyString());
		//System.out.println(res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(),201);
		JsonPath data=res.jsonPath();
		String j=data.getString("job");
		System.out.println(j);
		String id=data.getString("id");
		System.out.println(id);
		//String n=data.getString("name");
		//System.out.println(n);
		//Assert.assertEquals(n,"Hasen");
		//Assert.assertEquals(j,"software");
		 
		 
  }
  @Test(priority =2,enabled=false)
  public void putData(){
	  req=RestAssured.given();
	  	JSONObject obj=new JSONObject();
		obj.put("name","Praveen");
		obj.put("job","BussinessMan");
		req.body(obj.toJSONString());
		Response res=req.put("/api/users/2");
		System.out.println(res.asPrettyString());
		System.out.println(res.getStatusCode());
  }
  @Test(priority=3,enabled=false)
  public void patchData() {
	  req=RestAssured.given();
	  JSONObject obj=new JSONObject();
		obj.put("name","Praveen");
		obj.put("job","Chef");
		req.body(obj.toJSONString());
		Response res=req.patch("/api/users/2");
		System.out.println(res.asPrettyString());
		System.out.println(res.getStatusCode());
	  
  }
  @Test(priority=4,enabled=false)
  public void deleteData() {
	  req=RestAssured.given();
	  Response res=req.delete("/api/users/2");
		System.out.println(res.asPrettyString());
		System.out.println(res.getStatusCode());
  }
  @AfterTest
  public void deallocateMemory() {
	  req=null;
	  res=null;
  }

}
