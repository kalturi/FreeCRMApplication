package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.Utils.WebEventListener;



public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	
	public TestBase() {
	
		prop=new Properties();

		 
		try {
			
			FileInputStream fis = new FileInputStream("C:/Users/SUSHMITHA/eclipse-workspace/FreeCRMTest/src/main/java/com/crm/qa/config/Config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
    public WebDriver Intialization() {
    	
    String browser=prop.getProperty("browser");
    if(browser.equalsIgnoreCase("chrome")) {
    	System.setProperty("webdriver.chrome.driver", "C:/Program Files/seleniumDrivers/chromedriver.exe");	
    	driver=new ChromeDriver();
    }
    else if(browser.equalsIgnoreCase("FireFox")) {
    	System.setProperty("webdriver.chrome.driver", "C:/Program Files/seleniumDrivers/geckodriver.exe");	
    	driver=new FirefoxDriver();
    }
    
    //https://youtu.be/H2-3w-GQZ3g  ----for WebEventListner ,it is used to get the logs perfectly on console.
    e_driver= new EventFiringWebDriver(driver);
    WebEventListener refdriver=new WebEventListener();
    e_driver.register(refdriver);
    driver=e_driver;
    
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("Implicit_Wait")),TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("PAGE_LOAD_UTIL")), TimeUnit.SECONDS);
    
  driver.get(prop.getProperty("url"));
    
    	
    	return driver;
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
	
	
}
