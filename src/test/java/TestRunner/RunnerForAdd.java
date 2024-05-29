package TestRunner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\HKARPUDI\\OneDrive - Capgemini\\Documents\\RestAssured\\src\\test\\resource\\Features\\AddingUser.feature",
glue="Steps",tags="@users",plugin={"pretty","html:target/ReqresReport/UserReport.html",
		"json:target/JsonReport/UserReport.json","junit:target/JunitReport/UserReport.xml",
		
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
dryRun=true,monochrome=false)
public class RunnerForAdd extends AbstractTestNGCucumberTests{

}
