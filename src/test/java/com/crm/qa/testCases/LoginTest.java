package com.crm.qa.testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

public class LoginTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homepage;
	
	public LoginTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		Intialization();
		loginPage =new LoginPage(driver);
     }
	@Test(priority=1)
	public void validateTitle() {
		String ActualTitle=loginPage.validateTitleofPage();
		assertEquals("#1 Free CRM customer relationship management software clou", ActualTitle);
		
    }
	@Test(priority=2)
	public void validateLogin() throws InterruptedException {
		
		homepage=loginPage.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		driver.quit();
	
	}
	
	

}
