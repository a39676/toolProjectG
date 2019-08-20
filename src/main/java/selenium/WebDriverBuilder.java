package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverBuilder {

	public WebDriver buildFireFoxWebDriver(FirefoxOptions options) {
		BrowserType browserType = BrowserType.gecko;
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
		BrowserType browserType = BrowserType.edge;
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
		BrowserType browserType = BrowserType.chrome;
		System.setProperty(browserType.getDriver(), browserType.getPath());
		if(options == null) {
			options = new ChromeOptions();
//			options.addArguments(SeleniumConstant.headLess);
		}
		WebDriver driver = new ChromeDriver(options);
		return driver;
	}
	
	public WebDriver buildChromeWebDriver() {
		return buildChromeWebDriver(null);
	}
}
