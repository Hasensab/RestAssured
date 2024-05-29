package com.restasuured.RestAssured;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutUserDetails {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification req=RestAssured.given();
		JSONObject obj=new JSONObject();
		obj.put("name","Praveen");
		obj.put("job","BussinessMan");
		req.body(obj.toJSONString());
		Response res=req.put("/api/users/2");
		System.out.println(res.asPrettyString());
		System.out.println(res.asString());
		System.out.println(res.getBody());
		System.out.println(res.peek());
		System.out.println(res.getStatusCode());


	}

}
