package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Designing page object method

public class HomePage 
{
		public WebDriver driver ; // giving life to driver to find elements
   
      
             // creating constructor to give life to driver
			public HomePage(WebDriver driver) // Created constructor to accept anything as arguments// this driver is coming from test classes
			{
				// assigming test driver life to local driver
				 this.driver=driver ;  // catching & assigned to local driver from test driver// this driver means local driver of class
			}
			
			

			   private By SignIn=By.cssSelector("a[href*='sign_in']"); // defining objects @ top or defining elements
			   private By Title=By.cssSelector("div[class='text-center']");
			   private By NavBar=By.cssSelector("ul[class*='navbar-right']");
			   private By header=By.cssSelector("div[class*='video-banner'] h3");
			
			
			   
			   
			   public LoginPage getLogin()
			   {
				   
				   System.out.println("Trying to click on LoginIn option @ corner");
				   driver.findElement(SignIn).click();   // we are navigatingto another page so no return anything
				   LoginPage lp=new LoginPage(driver);
				  
				   return lp;
				   
			   }
			
			public WebElement getTitle()
			   {
				System.out.println("Trying to identify Central Title"); 
				  return driver.findElement(Title) ;  // defining methods @ bottom
				   
			   }
			
			public WebElement getNavBar()
			   {
				   System.out.println("Trying to identify Navigation Bar");
				   return driver.findElement(NavBar) ;  // defining methods @ bottom
				   
			   }


            public WebElement getHeader()
            {
            	
            	System.out.println("Trying to identify Header Text");
            	return driver.findElement(header);
            	
            }
	
}
