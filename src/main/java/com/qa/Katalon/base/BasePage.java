package com.qa.Katalon.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Abhishek Kumar
 *
 */
public class BasePage {
	public WebDriver driver;
	public Properties prop;

	/**
	 * This method is created to initialize driver using Property method browser
	 * name and according to browser entered in config.properties file this will
	 * initialize driver.
	 * 
	 * @param prop
	 * @return driver
	 */
	public WebDriver init_driver(Properties prop) {
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			driver = new SafariDriver();
		} else if (browser.equalsIgnoreCase("internet explorer")) {
			WebDriverManager.getInstance(InternetExplorerDriver.class).setup();
			driver = new InternetExplorerDriver();
		} else {
			System.out.println(browser + "is not found, please pass the correct browser");
		}
		driver.get(prop.getProperty("url"));
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().fullscreen();
		return driver;
	}

	/**
	 * This Method initializes property handles Exception while loading
	 * config.property file.
	 * 
	 * @return prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\mathu\\eclipse-workspace\\HubSpotPOMSeries\\src\\main\\java\\com\\qa\\Katalon\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
