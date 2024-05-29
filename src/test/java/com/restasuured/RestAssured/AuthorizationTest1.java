package com.restasuured.RestAssured;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizationTest1 {

	public static void main(String[] args) {
		RestAssured.baseURI="https://bookstore.toolsqa.com/";
		JSONObject obj=new JSONObject();
		obj.put("userName","Rajif");
		obj.put("password","Rajif@123");
		RequestSpecification req=RestAssured.given().auth().basic("Rajif","Rajif@123").
				header("Content-Type","application/json").body(obj.toJSONString());
		Response res=req.post("Account/v1/Authorized");
		String data=res.asString();
		System.out.println(data);
		
		JSONObject obj1=new JSONObject();
		obj1.put("userName","Rajif");
		obj1.put("password","Rajif@123");
		RequestSpecification req1=RestAssured.given().
				header("Content-Type","application/json").body(obj1.toJSONString());
			
		Response res1=req1.post("Account/v1/GenerateToken");
		
		System.out.println(res1.asString());

	}

}
