package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage {
	
	public WebDriver driver ;
	   
	        
		public ForgotPasswordPage(WebDriver driver) {  //Created constructor to accept anything as arguments// this driver is coming from test classes
			// TODO Auto-generated constructor stub
			 this.driver=driver ; //catching & assigned to local driver from test driver// this driver means local driver of class
		}



		private By inputEmailBox=By.id("user_email"); // defining objects @ top or defining elements
	   	private By sendMeInstructionBtn=By.cssSelector("[type='submit']");  // defining objects @ top or defining elements
	   
				

	   	
	   	public WebElement getEmail()
		   {
			   
	   		  System.out.println("Trying to pass the email ID for forgot password");
	   		  return driver.findElement(inputEmailBox) ;  // defining methods @ bottom
			   
		   }
	   	
	   	public WebElement sendMeInstructionsBtn()
	   	{
	   	   
	   		System.out.println("Trying to click on sendMeInstruction Button ");
	   		return driver.findElement(sendMeInstructionBtn) ; 
	   		
	   	}
}
