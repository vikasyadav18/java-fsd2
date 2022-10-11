package com.simplilearn.demo;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class ChromeAutomation {

	public static void main(String[] args) {

		System.out.println("Automation Starts!...\nInitializing Driver");
		System.out.println();

		// Step 1: Declare a path and set property for google chrome driver
		String path = "H:\\SoftwareTestingCoding\\chromeDriverAndseleniumDriver\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path);

		// Step 2: Initialize the webdriver
		WebDriver driver = new ChromeDriver();

		// Step3: give the base url
		String url = "https://www.flipkart.com/";
		driver.get(url);

		// Give time to page to load fully
		try {
			Thread.sleep(3000);

			WebElement cross = driver.findElement(By.cssSelector("body > div._2Sn47c > div > div > button"));
			cross.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// wait until webpage is loaded completely
		// Using FluentWait because it is Dynamic
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		// Maximize Window
		driver.manage().window().maximize();

		// Sending Querry as "iphone 13"
		WebElement q = driver.findElement(By.name("q"));
		q.sendKeys("iphone 13");

		// Clicking Search Button
		WebElement button = driver.findElement(By.cssSelector(
				"#container > div > div._1kfTjk > div._1rH5Jn > div._2Xfa2_ > div._1cmsER > form > div > button > svg"));
		button.click();

		// Again wait for atleast 5 seconds, so that the page is fully loaded
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Scroll down to an item
		// Scrolling is done through java script querry
		WebElement item = driver.findElement(
				By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[11]/div/div/div/a/div[2]/div[1]/div[1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", item);

		// Printing Result of an item to the console...
		System.out.println(item.getText());

		// Scrolling to bottom of the page
		// Again wait for atleast 5 seconds
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");

		System.out.println();
		System.out.println("Automation Ends!...\nClosing Driver...");
	}

}