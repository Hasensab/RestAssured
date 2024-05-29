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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;

public class ExcelReaderTest1 {
	RequestSpecification req;
	Response res;
	File file;
	FileInputStream fis;
	Workbook w;
	Sheet s;
	
	
	
  @BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in/";
  }

  @Test()
  
  public void addData() throws Exception{
	  file=new File("C:\\Users\\HKARPUDI\\OneDrive - Capgemini\\Documents\\RestAssured\\src\\test\\resource\\ExcelData\\Exceldata.xlsx");
	  fis=new FileInputStream(file);
	  w=new XSSFWorkbook(fis);
	  //s=w.getSheetAt(0);
	  s=w.getSheet("Data1");
	  int row=s.getPhysicalNumberOfRows();
	  //int row=s.getFirstRowNum();
	  //int row=s.getLastRowNum();
	  
	  int col=s.getRow(0).getPhysicalNumberOfCells();
	  String st=s.getSheetName();
	  System.out.println(st);
	  System.out.println("Rows:"+row+" col:"+col);
	  String name=s.getRow(3).getCell(0).toString();
	  String job=s.getRow(3).getCell(1).getStringCellValue();
	  
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

