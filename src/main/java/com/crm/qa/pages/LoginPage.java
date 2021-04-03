package com.crm.qa.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {           
        this.driver=driver;
	}
	
	private By lgnBtnonfirstpage=By.xpath("//span[text()='Log In']");
	private By email=By.cssSelector("[type='text']");
	private By password=By.cssSelector("[type='password']");
	private By lgnbtn=By.xpath("//div[text()='Login']");
	
	
	
	public HomePage validateLogin(String un ,String pwd) throws InterruptedException {
		 driver.findElement(lgnBtnonfirstpage).click();
		 Thread.sleep(2000);
		driver.findElement(email).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		 driver.findElement(lgnbtn).click();
		 return new HomePage(driver);
         }
	public String validateTitleofPage() {
		return driver.getTitle();
	}
	
	
	

	
}
