package selenium;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class JavaScriptCommonUtil {
	
	public static final String openNewTab = "window.open('%s','_blank');";

	public void openNewTab(WebDriver driver, String url) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if(StringUtils.isEmpty(url)) {
			url = "";
		}
		String script = String.format(openNewTab, url);
		jse.executeScript(script);
	}
	
	public void openNewTab(WebDriver driver) {
		openNewTab(driver, "");
	}
	
}
