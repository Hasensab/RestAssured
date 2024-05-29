package com.Testng;



import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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

public class HtmlReportGeneration {
	RequestSpecification req;
	Response res;
	FileInputStream fis;
	File file;
	Sheet s;
	Workbook wb;
	ExtentReports report;
	ExtentTest test;
	
	@DataProvider
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
	  report=new ExtentReports("C:\\Users\\HKARPUDI\\OneDrive - Capgemini\\Documents\\RestAssured\\test-output\\extent.html");
	  test=report.startTest("Adding users to excel & creating users");
  }

  @Test(dataProvider ="getData")
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
		/*Assert.assertEquals(n,name);
		Assert.assertEquals(n,name);
		Assert.assertEquals(j,job);
		Assert.assertEquals(j,job);*/
		if(job.equals(j)) {
			test.log(LogStatus.PASS,"Data is valid for expected"+job+" and actual "+j);
		}
		else if (name.isEmpty()) {
			test.log(LogStatus.INFO, "There is no name please provide name in excel sheet");
		}
		else if (job.isEmpty()) {
			test.log(LogStatus.SKIP, "job is not mandatory you can skip it");
		}
		else {
			test.log(LogStatus.FAIL, "Data is Invalid for expected"+job+" and actual "+j);
			
		}
		 
  }
  
  
  
  private void elseif() {
	// TODO Auto-generated method stub
	
}
@AfterTest
  public void deallocateMemory() {
	  req=null;
	  res=null;
	  report.endTest(test);
	  report.flush();
  }

}
