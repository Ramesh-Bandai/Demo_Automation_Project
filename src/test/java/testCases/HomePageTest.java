package testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.BaseClass;

public class HomePageTest extends BaseClass {
	
     public WebDriver driver; // to avoid driver overridden while test cases runs paralley but in sequentially we not need.Also public or static doent matter
	 public static Logger log =LogManager.getLogger(BaseClass.class.getName()); // should be in all test classes
	 HomePage hp; // to reuse life og homepage object w/o creating anothet object for second test cases in same class
	 
	 
		
	@BeforeTest
	public void initialize() throws IOException
	{
		driver=initializeDriver(); // this method returns driver object
		log.info("driver is initialized");
		
		driver.get(prop.getProperty("baseURL")); // hit on the browser
		log.info("Landed on home page");
	}
	
	
	@Test
	public void centralTitleValidation() throws IOException
	{
		 hp=new HomePage(driver);// giving the life as hp & sending life to Homepage hp @ local & also passing driver life 
		 log.info("home page object is created");
		
		// Compare the text from browser to Actual values
		
		Assert.assertEquals(hp.getTitle().getText(),"FEATURED COURSES" );// same page test cases should be in same class, same test or another test caes in same class
		log.info("sucessfully validated central big Texts on browser");
		
		
	}
	
	@Test
	public void navBarValidation()
	{
		Assert.assertTrue(hp.getNavBar().isDisplayed()); // same page test cases should be on same class
		log.info("Navigation Bar is displayed sucessfully");
		
	}
	
	@Test
	public void headerTextValidation()
	{
		Assert.assertEquals(hp.getHeader().getText(),"AN ACADEMY TO LEARN EVERYTHING ABOUT TESTING"); // same page test cases should be on same class
		log.info("Header text is displayed sucessfully");
		
	}
	
	@AfterTest
	public void TearDown()
	{
		driver.quit();
		log.info("Browser closed");
	
	}
	
	
}
