package com.Testng;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;

public class TestParameters1 {
	RequestSpecification req;
	Response res;
	
	
  @BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in/";
  }

  @Test()
  @Parameters({"name","job"})
  public void addData(@Optional("Rajif")String name,@Optional("Doctor")String job){
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
  
  
  
  @AfterTest
  public void deallocateMemory() {
	  req=null;
	  res=null;
  }

}
