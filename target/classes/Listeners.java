package resources;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


// Listeners class
public class Listeners extends BaseClass implements ITestListener {

	ExtentReports extent =ExtentReporterNG.getReportObject();
	
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();// keep ExtentTest class variables as safe, do not override by other classes
	
	
	public void onTestStart(ITestResult result) {
		// Before executing test I execute
		// Used for ExtentReport purpose to create entry
		
		 test=extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);//set test object into set.we are sending all new test objects into TreadLocal pool which is responsible for give right object
	}

	public void onTestSuccess(ITestResult result) {
	
		//test.log(Status.PASS,"Test Passes");
		extentTest.get().log(Status.PASS,"Test Passes");
		
	}

	public void onTestFailure(ITestResult result) {
		// code for screenshot & report section
		
		extentTest.get().fail(result.getThrowable()); // for extentreport // this method work for only sequential execution
		
		WebDriver driver=null; // for screenshot
		
		String TestMethodName=result.getMethod().getMethodName();
		
		
		//result.getTestClass().getRealClass().getDeclaredField("FieldName").get(result.getInstance());
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch(Exception e)
		{
			
		}
		
				
		try 
		{
			
			extentTest.get().addScreenCaptureFromPath(getScreenshotPath(TestMethodName,driver), result.getMethod().getMethodName());
			
			
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// I execute after all test cases execution
		// used to Flush
		
		extent.flush();
		//extentTest.get().flush();
		
	}

}
