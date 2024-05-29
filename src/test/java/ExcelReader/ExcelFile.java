package ExcelReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ExcelFile {
	RequestSpecification req;
	Response res;
	JsonPath path;
	JSONObject obj;
	String expN,expJ;
	FileInputStream fis;
	File file;
	Sheet s;
	Workbook w;
	//-------Doing for First senario(Adding  user)--------------
		/*Created By:Hasensab
		 * Reviewed By:Deepali
		 * Description:This step Adding users to the list
		 */
	@Given("user is on reqres url")
	public void user_is_on_reqres_url() {
		RestAssured.baseURI="https://reqres.in/";
		req=RestAssured.given();
		System.out.println("Given Step");
	    
	}
	
	/*@When("user enters the name and job")
	public void user_enters_the_name_and_job() throws IOException {
		file=new File("C:\\Users\\HKARPUDI\\OneDrive - Capgemini\\Documents\\RestAssured\\src\\test\\resource\\ExcelData\\data.xlsx");
		  fis=new FileInputStream(file);
		  w=new XSSFWorkbook(fis);
		  s=w.getSheet("data");
		  String name=s.getRow(0).getCell(0).toString();
		  String job=s.getRow(0).getCell(1).getStringCellValue();
		  
		 
			obj=new JSONObject();
			obj.put("name",name);
			obj.put("job",job);
			expN=name;
			expJ=job;
			req.headers("Content-Type","application/json");
			
		
			 
	    
		
	}*/
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
	@Test(dataProvider="CreateUser")
	@When("user enters the name and job")
	public void user_enters_the_name_and_job() throws IOException {
		file=new File("C:\\Users\\HKARPUDI\\OneDrive - Capgemini\\Documents\\RestAssured\\src\\test\\resource\\ExcelData\\data.xlsx");
		  fis=new FileInputStream(file);
		  w=new XSSFWorkbook(fis);
		  s=w.getSheet("data");
		  String name=s.getRow(0).getCell(0).toString();
		  String job=s.getRow(0).getCell(1).getStringCellValue();
		  
		 
			obj=new JSONObject();
			obj.put("name",name);
			obj.put("job",job);
			expN=name;
			expJ=job;
			req.headers("Content-Type","application/json");
			
		
			 
	    
		
	}
	@And("user hit the users API")
	public void user_hit_the_users_api() {
		res=req.body(obj.toJSONString()).post("/api/users");
		System.out.println("And step");
	    
	}

	@Then("users are added to list")
	public void users_are_added_to_list() {
		System.out.println(res.asPrettyString());
		path=res.jsonPath();
		String job1=path.getString("job");
		System.out.println(job1);
		Assert.assertEquals(job1,expJ);
		
	    
	}

}
