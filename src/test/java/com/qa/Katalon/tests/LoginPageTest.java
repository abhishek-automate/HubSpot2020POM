package com.qa.Katalon.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.Katalon.base.BasePage;
import com.qa.Katalon.page.LoginPage;
import com.qa.Katalon.utility.Constants;

public class LoginPageTest {
	BasePage basepage;
	WebDriver driver;
	LoginPage loginpage;
	Properties prop;

	@BeforeTest
	public void setUp() {
		basepage = new BasePage();
		prop = basepage.init_prop();
		driver = basepage.init_driver(prop);
		loginpage = new LoginPage(driver);
	}

	@Test(priority = 1)
	public void verifyLoginPageTitle() {
		String title = loginpage.getLoginPagetitle();
		Assert.assertEquals(title, Constants.Login_Page_Title);
	}

	@Test(priority = 2)
	public void verifySignupLink() {
		Assert.assertEquals(loginpage.checkSignUPLink(), true);
	}
	@Test(priority=3)
	public void loginPage() {
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
