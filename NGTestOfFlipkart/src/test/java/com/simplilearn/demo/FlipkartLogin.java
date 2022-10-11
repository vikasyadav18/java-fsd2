package com.simplilearn.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class FlipkartLogin {

	// Step 1: Initialize the webdriver
	WebDriver driver = null;
	SoftAssert soft = new SoftAssert();

	@Test
	public void initialization_T0() {
		// Step 2: Declare a path and set property for google chrome driver
		String path = "H:\\SoftwareTestingCoding\\chromeDriverAndseleniumDriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		
	}

	@Test(groups = "Chrome", dependsOnMethods = { "initialization_T0" })
	public void cross_T1() {
		System.out.println("Testcases Starting...");
		System.out.println();

		// starting chrome
		driver.get("https://www.flipkart.com/");

		try {
			Thread.sleep(5000);
			WebElement cross = driver.findElement(By.cssSelector("body > div._2Sn47c > div > div > button"));
			cross.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Test(groups = "Chrome", dependsOnMethods = {"cross_T1"})
	public void TitleTest_T2() {

		// wait until webpage is loaded completely
		// Using FluentWait because it is Dynamic
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);


		System.out.println("Fetching Title: " + driver.getTitle());
		System.out.println();

		AssertJUnit.assertEquals(driver.getTitle(),
				"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");

		soft.assertAll();
	}

	@Test(groups = "Chrome", dependsOnMethods = { "TitleTest_T2" })
	public void MaxWindow_T3() {

		// Maximize Window
		driver.manage().window().maximize();

	}

	@Test(groups = "Chrome", dependsOnMethods = { "MaxWindow_T3" })
	public void SendingQuerry_T4() {
		// Sending Querry as "iphone 13"
		WebElement q = driver.findElement(By.name("q"));
		q.sendKeys("iphone 13");

	}

	@Test(groups = "Chrome", dependsOnMethods = { "SendingQuerry_T4" })
	public void Search_T5() {
		// Clicking Search Button
		WebElement button = driver.findElement(By.cssSelector(
				"#container > div > div._1kfTjk > div._1rH5Jn > div._2Xfa2_ > div._1cmsER > form > div > button > svg"));
		button.click();
	}

	@Test(groups = "Chrome", dependsOnMethods = { "Search_T5" })
	public void Scroll_T6() {

		// Scroll down to an item
		// Scrolling is done through java script querry
		WebElement item = driver.findElement(
				
				By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[8]/div/div"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", item);

	}

//	@Test(groups = "Chrome", dependsOnMethods = { "Scroll_T6" })
//	public void CheckItem_T7() {
//		WebElement item = driver.findElement(
//				By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[12]/div/div/div/a/div[2]/div[1]/div[1]"));
//		try {
//			Thread.sleep(3000);
//			item.click();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		// Printing Result of an item to the console...
//		soft.assertEquals(item.getText(), item.getText(), "Item Title Matches...");
//		soft.assertAll();
//	}

	@AfterMethod
	public void afterMethod() {
		// wait for atleast 3 seconds, so that the page is fully loaded
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void Message2() {
		System.out.println("All Testcases Executed...");
		System.out.println();
		

	}

}