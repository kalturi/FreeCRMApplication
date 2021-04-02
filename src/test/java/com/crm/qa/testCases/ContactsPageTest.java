package com.crm.qa.testCases;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.Utils.TestUtil;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;

public class ContactsPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homepage;
	ContactsPage contactspage;
	String sheetName="contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		driver=Intialization();
		loginPage =new LoginPage(driver);
		homepage=loginPage.validateLogin(prop.getProperty("username"),prop.getProperty("password"));
		contactspage=homepage.clickOnContactsPage();
		
		
	}
	
	@Test(priority=1,enabled=false)
	public void ValidateContactsLabel() {
		String ActualLabel=contactspage.GetContactsText();
		assertEquals(ActualLabel,"Contacts");
	}
	@Test(priority=2,enabled=false)
	public void ValidateCheckBoxinContactsTable() throws InterruptedException {
		Thread.sleep(1000);
		contactspage.SelectContactsByName("sowmya kalturi");
		contactspage.SelectContactsByName("Susmitha kalturi");
		
	}
	@Test(priority=3,enabled=false)
	public void printTablevaluesonconsole() throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> rowvalues=new ArrayList<>();
		rowvalues=contactspage.getAlltheDataFromRow("Harsha Chatradh");
		 for (WebElement x : rowvalues) {
			 String value=x.getText();
			 System.out.println(value);
		 }
	}
	
	@DataProvider
	private Object[][] getTestNewContactsData() {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	
	
	
	//private void createNewForm(String fname,String lname,String Catogery,String Status,String Email,String phnNumber)
	@Test(priority=4, dataProvider="getTestNewContactsData",enabled=false)
	private void createNewForm(String fname,String lname,String Catogery,String Status,String Email,String phnNumber)
			throws InterruptedException {
		contactspage.ClickOnNewContactsButton(fname,lname,Catogery,Status,Email,phnNumber);
		//contactspage.ClickOnNewContactsButton("bahubali", "movie", "Contact", "On Hold", "Hu@gmail.com", "12345678");
		//contactspage.ClickOnNewContactsButton("bahubali", "sec", "Affiliate", "Terminated", "me@gmail.com", "23344");
}
	
	@Test(priority=1)
	private void deleteContactOnContactsPage()
			throws InterruptedException {
		contactspage.DeleteContact("Ramalakshmi Chatradhi");
	}
	
	
    @AfterMethod
	public void tearDown()  {
		//driver.quit();
	}
	
	
	
	
	

}

