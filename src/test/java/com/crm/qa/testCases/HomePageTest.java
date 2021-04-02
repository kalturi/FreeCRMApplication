package com.crm.qa.testCases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;

public class HomePageTest extends TestBase{
	
	
	LoginPage loginPage;
	HomePage homepage;
	ContactsPage contactsPage;
	TasksPage tasksPage;
	DealsPage dealsPage;
	
     public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		driver=Intialization();
		loginPage =new LoginPage(driver);
		contactsPage=new ContactsPage(driver);
		tasksPage=new TasksPage();
		dealsPage=new DealsPage();
	    homepage=loginPage.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void ValidatethetitleofHomePage() {
		String ActualTitleofHomePage= homepage.validateTiltleofHomePage();
		assertEquals(ActualTitleofHomePage, "Cogmento CRM");
	}
	@Test(priority=2)
	public void validateUser() {
		String actualUser= homepage.validateUser();
		assertEquals(actualUser, "Susmitha Kaltur");
	}
	@Test(priority=5)
	public void validateContactsPage() {
		
		contactsPage=homepage.clickOnContactsPage();
		 
	}
	@Test(priority=3)
	public void validateDealsPage() {
		dealsPage=homepage.clickOnDealsPage();
		 
	}
	@Test(priority=4)
	public void validateTasksPage() {
		tasksPage=homepage.clickOnTasksPage();
		 
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
			driver.quit();
	}

}
