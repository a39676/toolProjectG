package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import selenium.type.BrowserConfigType;

public class WebDriverBuilder {

	public WebDriver buildFireFoxWebDriver(FirefoxOptions options) {
		BrowserConfigType browserType = BrowserConfigType.gecko;
		System.setProperty(browserType.getDriver(), browserType.getPath());
		if(options == null) {
			options = new FirefoxOptions();
//			options.addArguments(SeleniumConstant.headLess);
		}
		WebDriver driver = new FirefoxDriver(options);
		return driver;
	}
	
	public WebDriver buildFireFoxWebDriver() {
		return buildFireFoxWebDriver(null);
	}
	
	public WebDriver buildEdgeWebDriver(EdgeOptions options) {
		BrowserConfigType browserType = BrowserConfigType.edge;
		System.setProperty(browserType.getDriver(), browserType.getPath());
		if(options == null) {
			options = new EdgeOptions();
		}
		WebDriver driver = new EdgeDriver(options);
		return driver;
	}
	
	public WebDriver buildEdgeWebDriver() {
		return buildEdgeWebDriver(null);
	}
	
	public WebDriver buildChromeWebDriver(ChromeOptions options) {
		BrowserConfigType browserType = BrowserConfigType.chrome;
		System.setProperty(browserType.getDriver(), browserType.getPath());
		WebDriver driver = null;
//		if(options == null) {
//			options = new ChromeOptions();
////			options.addArguments(SeleniumConstant.headLess);
//			driver = new ChromeDriver(options);
//		} else {
//			driver = new ChromeDriver();
//		}
		
		driver = new ChromeDriver();
		return driver;
	}
	
	public WebDriver buildChromeWebDriver() {
		return buildChromeWebDriver(null);
	}
}
