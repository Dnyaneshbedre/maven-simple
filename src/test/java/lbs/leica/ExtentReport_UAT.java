package lbs.leica;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport_UAT
{
public static ExtentHtmlReporter htmlReporter;
public static ExtentReports extent;
public static ExtentTest test;
WebDriver driver;

@BeforeSuite
public void setUp() 
{
	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/ZensarReport.html");
	extent = new ExtentReports();
	extent.attachReporter(htmlReporter);

	extent.setSystemInfo("OS", "Windows");
	extent.setSystemInfo("Host Name", "Leica");
	extent.setSystemInfo("Environment", "UAT");
	extent.setSystemInfo("User Name", "Dnyanesh");

	htmlReporter.config().setChartVisibilityOnOpen(true);
	htmlReporter.config().setDocumentTitle("Automation Test Report for Leica environment");
	htmlReporter.config().setReportName("Automation Test Report for Leica environment");
	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTheme(Theme.DARK);
	//htmlReporter.config().setTheme(Theme.STANDARD);
}



@AfterMethod
public void getResult(ITestResult result) throws IOException 
{
	if (result.getStatus() == ITestResult.FAILURE) 
	{
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",ExtentColor.RED));
		test.fail(result.getThrowable());
	} 
	else if (result.getStatus() == ITestResult.SUCCESS) 
	{
		test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
	} 
	else 
	{
		test.log(Status.SKIP,MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
		test.skip(result.getThrowable());
	}
}

@AfterSuite
public void tearDown() 
{
extent.flush();
}

public void reportLog(String message) 
{    
 	test.log(Status.INFO, message);
    Reporter.log(message);
}
}