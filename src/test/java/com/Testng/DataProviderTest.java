package com.Testng;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;

public class DataProviderTest {
	RequestSpecification req;
	Response res;
	
	@DataProvider(name="CreateUser")
	public Object[][] dp() {
		Object[][] data=new Object[2][2];
		data[0][0]="Hasen";
		data[0][1]="software";
		
		data[1][0]="Praveen";
		data[1][1]="Bussiness";
		return data;
		
	}
  @BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in/";
  }

  @Test(priority = 1,dataProvider="CreateUser")
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
		String n=data.getString("name");
		System.out.println(n);
		Assert.assertEquals(n,name);
		Assert.assertEquals(n,name);
		Assert.assertEquals(j,job);
		Assert.assertEquals(j,job);
		 
		 
  }
  @Test(priority =2,enabled=true)
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
