package com.crm.qa.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase {

	WebDriver driver;
	Actions a;

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		Actions a = new Actions(driver);
		this.a = a;

	}

	By mouseOver = By.xpath("//div[contains(@class,'ui right floated')]");
	By contactsbtn = By.xpath("//div[text()='Contacts']");
	By createContactBtn = By.xpath("//button[contains(@class,'ui linkedin button')][text()='Create']");
	By EmailText = By.xpath("//input[@placeholder='Email address']");
	By PhoneNumber = By.xpath("//input[@placeholder='Number']");
	By SaveNewContactForm = By.xpath("//button[contains(@class,'ui linkedin button')][text()='Save']");
	By delContactBtn = By.xpath("//a[text()='Honey Chatradhi']/parent::td/following-sibling::td[6]/div/button");

	public String GetContactsText() {
		return driver.findElement(contactsbtn).getText();
	}

	public void SelectContactsByName(String name) {
		a.moveToElement(driver.findElement(mouseOver)).perform();
		driver.findElement(By.xpath("//a[text()='" + name + "']/parent::td/preceding-sibling::td/div")).click();

	}

	public List<WebElement> getAlltheDataFromRow(String name) {
		List<WebElement> arr = new ArrayList<WebElement>();
		List<WebElement> rowvalues = new ArrayList<WebElement>();
		a.moveToElement(driver.findElement(mouseOver)).perform();
		int count = 0;
		arr = driver.findElements(By.xpath("//table[contains(@class,'custom-grid')]/tbody/tr/td[2]"));
		for (WebElement a : arr) {
			String value = a.getText();
			count++;
			if (name.equalsIgnoreCase(value)) {
				rowvalues = driver
						.findElements(By.xpath("//table[contains(@class,'custom-grid')]/tbody/tr[" + count + "]"));
				break;
			}

		}

		return rowvalues;
	}

	public void ClickOnNewContactsButton(String fname, String lname, String cat, String statusdrop, String Email,
			String phnNumber) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(createContactBtn).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys(fname);
		;
		driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(lname);
		;
		driver.findElement(By.xpath("//div[@name='category']//i[contains(@class,'dropdown icon')]")).click();
		List<WebElement> catogeries = driver
				.findElements(By.xpath("//div[contains(@class,'visible menu transition')]"));

		for (WebElement x : catogeries) {
			System.out.println(x.getText());
			if (x.getText().equalsIgnoreCase(cat)) {
				System.out.println("After entering in If " + x.getText());
				x.click();
				break;
			}

			Thread.sleep(1000);

		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@name='status']//i[contains(@class,'dropdown icon')]")).click();
		List<WebElement> status = driver.findElements(By.xpath("//div[contains(@class,'visible menu transition')]"));
		Thread.sleep(1000);
		/*
		 * for(WebElement y: status) System.out.println(y.getText());
		 */

		for (WebElement eachstatus : status) {
			String ActualStatus = eachstatus.getText();
			if (ActualStatus.contains(statusdrop)) {
				// System.out.println("It is clicked "+ActualStatus);
				eachstatus.click();
			}
		}
		Thread.sleep(1000);
		driver.findElement(EmailText).sendKeys(Email);
		driver.findElement(PhoneNumber).sendKeys(phnNumber);
		Thread.sleep(1000);
		driver.findElement(SaveNewContactForm).click();
		Thread.sleep(1000);
	}

	public void DeleteContact(String name) throws InterruptedException {
		a.moveToElement(driver.findElement(mouseOver)).perform();
		int pageCount = driver.findElements(By.xpath("//div[contains(@class,'custom-pagination')]/a")).size();
		List<WebElement> pages = driver.findElements(By.xpath("//div[contains(@class,'custom-pagination')]/a"));
		Thread.sleep(20000);
		List<WebElement> arr = new ArrayList<WebElement>();
		arr = driver.findElements(By.xpath("//table[contains(@class,'custom-grid')]/tbody/tr/td[2]"));
		int i = 2;
		Iterator it = pages.iterator();

		while (i <= pageCount) {

			// for(int i=2;i<=pageCount;i++) {
			for (int retry = 0; retry < 5; retry++) {
				try {
					for (WebElement x : arr) {
						// x.getText();
						if (x.getText().equalsIgnoreCase(name)) {
							driver.findElement(
									By.xpath("//a[text()='" + name + "']/parent::td/preceding-sibling::td/div"))
									.click();
							Thread.sleep(1000);
							driver.findElement(By
									.xpath("//a[text()='" + name + "']/parent::td/following-sibling::td[6]/div/button"))
									.click();
							driver.findElement(By.xpath("//button[text()='Delete']")).click();
							Thread.sleep(10000);
							System.out.println("Deleted successfully");
						}
					}
               }

				catch (StaleElementReferenceException s) {
					driver.navigate().refresh();
					Thread.sleep(1000);
					arr = driver.findElements(By.xpath("//table[contains(@class,'custom-grid')]/tbody/tr/td[2]"));
				}

			}
			System.out.println("incrementing the page");
			i++;
			driver.findElement(By.xpath("//div[contains(@class,'custom-pagination')]/a[" + i + "]")).click();
			System.out.println("page count " + i);
			arr = driver.findElements(By.xpath("//table[contains(@class,'custom-grid')]/tbody/tr/td[2]"));
		}

	}
}
