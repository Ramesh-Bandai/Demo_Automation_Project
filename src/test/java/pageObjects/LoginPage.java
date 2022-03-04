package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage 
{
   public WebDriver driver ;
   
   
   // constructor     
	public LoginPage(WebDriver driver) // Created constructor to accept anything as arguments// this driver is coming from test classes
	{
		
		 this.driver=driver ;  // catching & assigned to local driver from test driver// this driver means local driver of class
	}
   
   
	// all variables should be private and methods should be public
	private By inputEmailBox=By.id("user_email"); // defining objects @ top or defining elements
    private By inputPasswordBox=By.cssSelector("[type='password']"); // defining objects @ top or defining elements
    private By loginBtn=By.cssSelector("[value='Log In']");  // defining objects @ top or defining elements
    private By forgotPassword=By.cssSelector("[href*='password/new']");
			
			
			
			public WebElement getEmail()
			   {
				
				   System.out.println("Trying to Enter Email ID to Login");
				   return driver.findElement(inputEmailBox) ;  // defining methods @ bottom
				   
			   }
			
			public WebElement getPassword()
			   {
				  System.out.println("Trying to Entet Password to Login");
				  return driver.findElement(inputPasswordBox) ;  // defining methods @ bottom
				   
			   }
			
			
			public WebElement getLoginBtn()
			   {
				  
				 System.out.println("Trying to click on Login Button to Login");
				 return driver.findElement(loginBtn) ;  // we are not navigating to another page so return element
				   
			   }
			
			public ForgotPasswordPage getForgotPassword()
			{
				System.out.println("Trying to click on Forgot Password link");
				driver.findElement(forgotPassword).click(); // insted of returning we click becuase it is navigating to another page it is specia; object
				ForgotPasswordPage fp=new ForgotPasswordPage(driver);
				return fp;
			}



	
}

