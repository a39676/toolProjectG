package selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class Selenium {

	public static void testSelenium() throws InterruptedException, IOException {
		String browserPath = null;
//		browserPath = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe";
//		browserPath = "C:/Program Files/Mozilla Firefox/firefox.exe";
		browserPath = "D:/auxiliary/tmp/geckodriver-v0.24.0-win64/geckodriver.exe";
//		// Optional, if not specified, WebDriver will search your path for chromedriver.
		
		String browserDriver = null;
//		browserDriver = "webdriver.chrome.driver";
//		browserDriver = "webdriver.firefox.marionette";
		browserDriver = "webdriver.gecko.driver";
		System.setProperty(browserDriver, browserPath);
		
		FirefoxOptions options = new FirefoxOptions();
//		options.addArguments("--headless");
		
		Long startTime = System.currentTimeMillis();
		System.out.println("before build driver: " + startTime);
		WebDriver driver = new FirefoxDriver(options);
		Long endTime = System.currentTimeMillis();
		System.out.println("after build driver: " + endTime + " cost: " + (endTime - startTime));
		
		driver.get("http://www.baidu.com");
		System.out.println("get?");
		Thread.sleep(1000); // Let the user actually see something!
		System.out.println("after first sleep");
		WebElement searchBox = driver.findElement(By.id("kw"));
		searchBox.sendKeys("test");
		searchBox.submit();
		Thread.sleep(1000); // Let the user actually see something!
		WebElement imageButton = driver.findElement(By.partialLinkText("图片"));
		imageButton.click();
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("d:/auxiliary/tmp/screenshot.png"));
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		testSelenium();
	}
}
