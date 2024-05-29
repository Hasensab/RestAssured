package com.restasuured.RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteUser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification req=RestAssured.given();
		Response res=req.delete("/api/users/2");
		System.out.println(res.asPrettyString());
		System.out.println(res.asString());
		System.out.println(res.getBody());
		System.out.println(res.peek());
		System.out.println(res.getStatusCode());

	}

}
