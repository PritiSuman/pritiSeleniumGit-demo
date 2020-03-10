package Academy;

import java.io.IOException;




import org.testng.Assert;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import pageObjects.LandingPage;

import resources.Log;
import resources.base;

public class validateTitle extends base{


	
	@BeforeTest
	public void initialize() throws IOException
	{
		Dlog4j.configurationFile = "C:\\Users\\priti\\eclipse-workspace\\E2EProject\\E2EProject\\src\\main\\java\\resources\\log4j2.xml";
		 driver =initializeDriver();
		 Log.info("Driver is initialized");
		 String sTestCaseName = "ValidateAppTitle";
		 Log.startTestCase(sTestCaseName);
			
		driver.get(prop.getProperty("url"));
		 Log.info("Navigated to Home page");
	}
	@Test
	
	public void validateAppTitle() throws IOException
	{
		
		//one is inheritance
		// creating object to that class and invoke methods of it
		LandingPage l=new LandingPage(driver);
		//compare the text from the browser with actual text.- Error..
		Assert.assertEquals(l.getTitle().getText(), "FEATURED CO123URSES");
		 Log.info("Successfully validated Text message");
		 System.out.println("Test completed");
		 
		
	
		
		}
	@AfterTest
	public void teardown()
	{
		String sTestCaseName = "ValidateAppTitle";
		 Log.endTestCase(sTestCaseName);
		driver.close();
		driver=null;
		
	}

	

	
}
