package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	WebDriver driver;
	Actions a;
	public HomePage(WebDriver driver2) {
		this.driver=driver2;
		Actions a=new Actions(driver);
		this.a=a;
		
		}
	//Test cases should not be dependent on each other
	//Every test case should launch the browser and close the browser
	
	By user=By.xpath("//span[contains(text(),'Susmitha')]");
	By contacts=By.xpath("//span[contains(text(),'Contacts')]");
	By deals=By.xpath("//span[contains(text(),'Deals')]");
	By tasks=By.xpath("//span[contains(text(),'Tasks')]");
	By settingsBtn=By.xpath("//div[contains(@class,'item dropdown')]");
	By LogoutBtn=By.xpath("//span[text()='Log Out']");
	
	
	public String validateTiltleofHomePage() {
		return driver.getTitle();
	}
	public String validateUser() {
		return driver.findElement(user).getText();
		
	}
	
	public ContactsPage clickOnContactsPage() {
		
		a.moveToElement(driver.findElement(contacts)).click().build().perform();
		return new ContactsPage(driver);
		
	}
	
	public DealsPage clickOnDealsPage() {
		
		a.moveToElement(driver.findElement(deals)).click().build().perform();
		return new DealsPage();
	}

	public TasksPage clickOnTasksPage() {
		
		a.moveToElement(driver.findElement(tasks)).click().build().perform();
		return new TasksPage();
	}
	
	public LoginPage clickOnLogOut() {
		driver.findElement(settingsBtn).click();
		driver.findElement(LogoutBtn).click();
		return new LoginPage(driver);
		
	}
	

	
	
	
	
	
	
	
}
