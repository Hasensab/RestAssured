package com.restasuured.RestAssured;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification req=RestAssured.given();
		JSONObject obj=new JSONObject();
		obj.put("name","Hasen");
		obj.put("job","software");
		req.body(obj.toJSONString());
		Response res=req.post("/api/users");
		System.out.println(res.asPrettyString());
		System.out.println(res.asString());

	}

}
