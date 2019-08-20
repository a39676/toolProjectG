package selenium;

public enum BrowserType {
	
	/** chrome */
	chrome("chrome", "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe", "webdriver.chrome.driver"),
	/** fireFox */
	fireFox("fireFox", "C:/Program Files/Mozilla Firefox/firefox.exe", "webdriver.firefox.marionette"),
	/** gecko */
	gecko("gecko", "D:/soft/geckodriver-v0.24.0-win64/geckodriver.exe", "webdriver.gecko.driver"),
	/** edge */
	edge("edge", "D:/soft/MicrosoftWebDriver.exe", "webdriver.edge.driver"),
	;
	
	private String name;
	private String path;
	private String driver;
	
	BrowserType(String name, String path, String driver) {
		this.name = name;
		this.path = path;
		this.driver = driver;
	}
	
	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}
	
	public String getDriver() {
		return driver;
	}

	public static BrowserType getType(String typeName) {
		for(BrowserType t : BrowserType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
}
