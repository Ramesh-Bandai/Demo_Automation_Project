package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass 
{   
  // driver object overridden problem solved smartly by creating local copy in each test class
  //public static WebDriver driver;// if multiple test cases acessing this driver it will not create new object.it will share existing copy only.but it can be overide by another method w/o creating new
	public WebDriver driver; //to pass driver in another classes by keeping copy here or to acess ouside of the class
	public Properties prop;  //to make class method variables to class variables to access anywhere
	public XSSFWorkbook workbook;
         
		  public WebDriver initializeDriver() throws IOException
	         {
			  //Syntax to access properties file variables in three steps
	        	prop=new Properties(); //Creating object of properties class
	        	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//data.properties") ; // creating object of FileInputString Class & passing file path
	        	prop.load(fis); 
	         // prop.getProperty("variable name");
	         // mvn test -Dbrowser=chrome  whenever you see browser parameter set that value to chrome. specially in CMD
	        	
	          //String browserName=System.getProperty("browser"); // to accepts parameters from CMD or jenkin parameters
	            String browserName=prop.getProperty("browser"); //to accepts parameters from data.properties file
	    		if (browserName.contains("Chrome")) 
	    		{
	    			//C:\\Users\\user\\eclipse-workspace\\MyStoreProject\\Drivers\\chromedriver.exe  
	    			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
	    			
	    			
	    			// To run in headless mode
	    			ChromeOptions options=new ChromeOptions();
	    			
	    			if (browserName.contains("headless")) 
	    			{
	    			    options.addArguments("headless"); // but to run headless selenium version should be latest
	    			}
	    			
	    			// creating driver object to perform actions
	    			driver=new ChromeDriver(options) ; // passed options inside to change browser beheviour. even if options is blank no problem
	    			
	    		} else if (browserName.contains("FireFox"))
	    		{
	    			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
	    			driver=new FirefoxDriver();
	    			
	    		} else if (browserName.contains("IE")) 
	    		{
	    			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"//Drivers//IEDriverServer.exe");
	    			driver=new InternetExplorerDriver();
	    			
	    		} else if (browserName.contains("ME")) 
	    		{
	    			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"//Drivers//msedgedriver.exe");
	    			driver=new EdgeDriver();
	    			
	    		}
	    		
	    		
	    		
	    		
	    		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    		
	    		return driver; // to pass in another classes
	    		
	    			    			    		
	         }
	   
	   
		  
		  public String getScreenshotPath(String TestCaseName,WebDriver driver) throws IOException // here argument driver coming from testNG listners
			{
			    TakesScreenshot ts=(TakesScreenshot) driver;
			    
			   File source= ts.getScreenshotAs(OutputType.FILE);
			   String destinationFile=System.getProperty("user.dir")+"\\reports\\"+TestCaseName+".png";
			   FileUtils.copyFile(source, new File(destinationFile));
			   
			   return destinationFile; // for attaching screenshot image path to report
				
			}
			
	
	       public ArrayList<String> getExcelData(String testCaseName) throws IOException
	       {
	    	   ArrayList<String> a=new ArrayList<String>();
	   		
	   		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//java//resources//data.xlsx");
	   		XSSFWorkbook workbook=new XSSFWorkbook(fis);
	   		int sheets=workbook.getNumberOfSheets();
	   		for(int i=0;i<sheets;i++) // iterate all the sheet & check expected sheet
	   		   {
	   			   //System.out.println(workbook.getSheetName(i)); // Displays all sheets in workbook
	   			   if(workbook.getSheetName(i).equalsIgnoreCase("sheet1")) // checking the expected sheet
	   			   {
		   			       XSSFSheet sheet= workbook.getSheetAt(i);// get that matched sheet place it here
		   			       Iterator<Row> rows=sheet.iterator(); //it iterate all the rows of that sheet (row by row) //sheet is collection of rows
		   			       Row firstrow=rows.next();// gives first row of that sheet (upper left corner of sheet)
		   			       Iterator<Cell> cell=firstrow.cellIterator();// row is collection of cells
		   			     //cell.next();// moves to next cell
		   			      
		   			       int k=0; // to get column number
		   			       int column=0;
		   			       while(cell.hasNext())// just check next cell is present or not
		   			       {
		   			    	   Cell value=cell.next(); // now it Actually moves next cell
		   			    	  // System.out.println(value.getStringCellValue());
		   			    	   
		   			    	   if(value.getStringCellValue().equalsIgnoreCase("testCases")) // checking desired column name from all cells
		   			    	   {
		   			    		   
		   			    		   column=k;
		   			    		   
		   			    	   }
		   			    	   
		   			    	   
		   			    	   
		   			    	   k++;//increaments after one while loop completes
		   			    	   
		   			        }
		   			       
		   			      // System.out.println(column);// Above we have targeted expected column text & that text column index
		   			       
		   			       while(rows.hasNext()) // scanning only for above expected column
		   			       {
		   			    	 Row row= rows.next(); 
		   			    	 if(row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) // only scanning all th evalues of that respective column which we have identified
		   			    	 {
		   			    		// got acess to specific haeder row 
		   			    		Iterator<Cell> cellValue= row.cellIterator();
		   			    		
		   			    		while(cellValue.hasNext())
		   			    		{
		   			    			
		   			    			Cell c=cellValue.next();
		   			    			
		   			    			if(c.getCellTypeEnum()==CellType.STRING)
		   			    			{
		   			    			    //System.out.println(cellValue.next().getStringCellValue()); // checking on consol
			   			    			a.add(c.getStringCellValue()); // storing all values in array  having name a declered at top
			   			    			
		   			    				
		   			    			}else
		   			    			{
		   			    				a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
		   			    				
		   			    			}
		   			    			
		   			    		}
		   			    		 
		   			    	 }
		   			    	 
		   			    	
		   			    	   
		   			       }
		   			       
	   			      
	   			      	   			          	      			       
	   			       	   			       
	   			    }//if loop

	  
	   		     }//for loop
			     
	   		       return a;   // a Array will be retured back
	   		
	   		
	         }//getexcelData Method 
	       
	       
}//Base class
