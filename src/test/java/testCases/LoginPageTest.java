package testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import resources.BaseClass;

public class LoginPageTest extends BaseClass {
	 public WebDriver driver; // to avoid driver overridden while test cases runs paralley but in sequentially doesnt matter
	 public static Logger log =LogManager.getLogger(BaseClass.class.getName()); // should be in all test classes
	 
	 HomePage hp;
	 LoginPage lp;
	 ForgotPasswordPage fp;
	 
	 
	   
    @BeforeTest // just for initializing driver
    public void invokeBrowser() throws IOException
    {
    	driver=initializeDriver(); // this method returns driver object
    	log.info("driver is initialized");
    	//driver.get(prop.getProperty("baseURL")); // hit on the browser, skip in case of login type test. in this case hit browser inside the test cases
    	
    }
	
		 
	@Test(dataProvider="getData")
	public void loginTestValidation(String userName,String password) throws IOException, InterruptedException
	{
		//driver=initializeDriver(); // this method returns driver object
		driver.get(prop.getProperty("baseURL")); // hit on the browser inside the test for only login cases
		log.info("Landed on home page");
		hp=new HomePage(driver);// sending above driver life to Homepage
		log.info("home page object is created");
		
		
		lp=hp.getLogin(); // catching object from getLogin method with return type
		lp.getEmail().sendKeys(userName); // it should be from TestNG data provider
		lp.getPassword().sendKeys(password);  // it should be from TestNG data provider
		
		Thread.sleep(2000);
		lp.getLoginBtn().click();
		log.info("Clickd on login Button by passing username & password");
		
		
	}
	
	//above test is considered as single test for single user
	// checking above same flow for diffrent login data
	// Parameterizing the above test
	@DataProvider()
	public Object[][] getData()
	{
		Object[][] data=new Object[2][2]; //[no of tests] [no of values to be passes at a time] or 2 rows with 2 columns (user&pass)
		
		data[0][0]="krishna@gmail.com";
		data[0][1]="real@01";
		data[1][0]="radha@gmail.com";
		data[1][1]="real@02";
		
		return data; // return data to acess data on top of test
	}
	
	@Test
	public void forgotPasswordValidation()
	{
		driver.get(prop.getProperty("baseURL")); // hit on the browser inside the test for only login cases
		log.info("Landed on home page");
		hp=new HomePage(driver);// sending above driver life to Homepage
		log.info("home page object is created");
		
		
		lp=hp.getLogin(); // catching object from getLogin method with return type
		fp=lp.getForgotPassword();
		fp.getEmail().sendKeys("HareKrishna@gmail.com");
		fp.sendMeInstructionsBtn().click();
		log.info("Clicked on sendMeInstruction Button by passing Email ID");
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
		log.info("Browser is closed");
		
	}
	
}
