package com.qa.Katalon.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.Katalon.base.BasePage;
import com.qa.Katalon.utility.ElementUtil;

/**
 * Login Page class contains Corresponding By locator and Methods which will be
 * Implemented in loginPageTest class.
 * 
 * @author Abhishek Kumar
 *
 */
public class LoginPage extends BasePage {
	WebDriver driver;
	ElementUtil eUtil;
	By username = By.id("username");
	By password = By.id("password");
	By clickBtn = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	By forgotPassword = By.linkText("Forgot my password");

	/**
	 * This is LoginPage Constructor to assign driver class variable with BasePage
	 * driver sessionID
	 * 
	 * @param driver
	 */
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementUtil(driver);
	}

	/**
	 * This method is used to get LoginPage Title.
	 * 
	 * @return String
	 */
	public String getLoginPagetitle() {
		return eUtil.doGetTitle();
	}

	/**
	 * This Method is written to check if SignUp Link is displayed
	 * 
	 * @return Boolean
	 */
	public boolean checkSignUPLink() {
		return eUtil.doIsDisplayed(signUpLink);
	}

	/**
	 * This Method is written to check if forgot password link is visible.
	 * 
	 * @return Boolean
	 */
	public Boolean checkForgotPassword() {
		return eUtil.doIsDisplayed(forgotPassword);
	}

	/**
	 * This Method is written to LoginPage login using By locator and parameterized
	 * username & password value.
	 * 
	 * @param userName
	 * @param pwd
	 * @return HomePage
	 */
	public HomePage doLogin(String userName, String pwd) {
		eUtil.doSendKeys(username, userName);
		eUtil.doSendKeys(password, pwd);
		eUtil.doClick(clickBtn);
		return new HomePage();
	}
}
