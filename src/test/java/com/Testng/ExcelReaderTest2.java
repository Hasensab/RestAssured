package com.Testng;



import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;

public class ExcelReaderTest2 {
	RequestSpecification req;
	Response res;
	FileInputStream fis;
	File file;
	Sheet s;
	Workbook wb;
	
	@DataProvider(name="CreateUser")
	public Object[][] getData() throws IOException {
		file=new File("C:\\Users\\HKARPUDI\\OneDrive - Capgemini\\Documents\\RestAssured\\src\\test\\resource\\ExcelData\\Exceldata.xlsx");
		fis=new FileInputStream(file);
		wb=new XSSFWorkbook(fis);
		s=wb.getSheet("Data1");
		int row=s.getPhysicalNumberOfRows();
		int col=s.getRow(0).getPhysicalNumberOfCells();
		Object[][] data=new Object[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				data[i][j]=s.getRow(i).getCell(j).toString();
				
			}
		}
		
		return data;
		
	}
  @BeforeTest
  public void init() {
	  RestAssured.baseURI="https://reqres.in/";
  }

  @Test(dataProvider="CreateUser")
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
  
  
  
  @AfterTest
  public void deallocateMemory() {
	  req=null;
	  res=null;
  }

}
