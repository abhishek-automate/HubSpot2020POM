package com.qa.Katalon.utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author Abhishek Kumar This ElementUtil Class file consist of Utility of
 *         Select class standard action, Custom select option, jQuery DropDown
 *         and different actions performed on WebElement
 */

public class ElementUtil {
	WebDriver driver;
	Actions action;
	WebDriverWait eWait;

	/**
	 * Constructor of ElementUtil class which takes driver as parameter and assign
	 * to class variable
	 * 
	 * @param driver
	 */
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method find element in WebPage passing By locator
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement doFindElement(By locator) {
		return driver.findElement(locator);
	}

	/**
	 * This Method utility file is used to Perform send keys on WebElement
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		doFindElement(locator).sendKeys(value);
	}

	/**
	 * This method is used to click on WebElement
	 * 
	 * @param locator
	 */

	public void doClick(By locator) {
		doFindElement(locator).click();
	}

	/**
	 * This method is used to select from DropDown by values written using selenium
	 * Select class and selectByValue method
	 * 
	 * @param locator
	 * @param value
	 */

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(doFindElement(locator));
		select.selectByValue(value);

	}

	/**
	 * This method from DropDown select element by Index using Selenium Select class
	 * selectByIndex method.
	 * 
	 * @param locator
	 * @param index
	 */
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(doFindElement(locator));
		select.selectByIndex(index);
	}

	/**
	 * This method from DropDown select element by visibleText using Selenium Select
	 * class and selectByVisibleText method
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectByVisibleText(By locator, String value) {
		Select select = new Select(doFindElement(locator));
		select.selectByVisibleText(value);
	}

	/**
	 * This method using selenium method getCurrentURL gets current URL webPage
	 * open.
	 * 
	 * @return String
	 */
	public String doGetCurrentURL() {
		return driver.getCurrentUrl();
	}

	/**
	 * This method closes current active sessionID browser.
	 */
	public void doclose() {
		driver.close();
	}

	/**
	 * This method closes all active browser sessionID and nullify them
	 */
	public void doQuit() {
		driver.quit();
	}

	/**
	 * This method get Title of current webPage
	 * 
	 * @return
	 */
	public String doGetTitle() {
		return driver.getTitle();
	}

	/**
	 * This method is written to check if WebElement is displayed
	 * 
	 * @param locator
	 * @return Boolean
	 */
	public Boolean doIsDisplayed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	/**
	 * This method get all values from DropDown and click on which is provided in
	 * parameter
	 * 
	 * @param locator
	 * @param value
	 */

	public void doSelectGetOptions(By locator, String value) {
		Select select = new Select(doFindElement(locator));
		List<WebElement> options = select.getOptions();
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().contains(value)) {
				options.get(i).click();
				break;
			}
		}
	}

	/**
	 * This method using select class and getOptions Method capture values from drop
	 * down and store in list.
	 * 
	 * @param locator
	 * @return List<String>
	 */

	public List<String> doGetDropDownvalues(By locator) {
		List<String> ar = new ArrayList<String>();
		Select select = new Select(doFindElement(locator));
		List<WebElement> dropdownList = select.getOptions();
		for (int i = 0; i < dropdownList.size(); i++) {
			ar.add(dropdownList.get(i).getText());
		}
		return ar;
	}

	/**
	 * This is custom Method created without using Selenium standard Select class
	 * and if CSS/Xpath accordingly perform action
	 * 
	 * @param value
	 * @param locatorValue
	 * @param textValue
	 */

	public void doClickjQueryDropDown(String value, String locatorValue, String textValue) {
		List<WebElement> dropDownValue = null;
		if (value.contains("xpath")) {
			dropDownValue = driver.findElements(By.xpath(locatorValue));
		} else if (value.contains("css")) {
			dropDownValue = driver.findElements(By.cssSelector(locatorValue));
		}

		for (int i = 0; i < dropDownValue.size(); i++) {
			if (dropDownValue.get(i).getText().contains(textValue)) {
				dropDownValue.get(i).click();
			}
		}
	}

	/**
	 * This Method is used to handle jQuery DropDown either Single/Multiple/All
	 * CheckBoxes it can select based on input provided to parameter "dropDownValue"
	 * which is created as a Array using three dot operator
	 * 
	 * @param value
	 * @param locatorValue
	 * @param dropDownValue
	 */
	public void dojQueryMultiDropDown(String value, String locatorValue, String... dropDownValue) {
		List<WebElement> jQuerydropDown = null;
		if (value.contains("xpath")) {
			jQuerydropDown = driver.findElements(By.xpath(locatorValue));
		} else if (value.contains("css")) {
			jQuerydropDown = driver.findElements(By.cssSelector(locatorValue));
		}
		if (!dropDownValue[0].equalsIgnoreCase("ALL")) {
			for (int i = 0; i < jQuerydropDown.size(); i++) {
				String text = jQuerydropDown.get(i).getText();
				for (int k = 0; k < dropDownValue.length; k++) {
					if (text.equals(dropDownValue[k])) {
						jQuerydropDown.get(i).click();
						break;
					}
				}

			}
		} else {
			try {
				for (int all = 0; all < jQuerydropDown.size(); all++) {
					jQuerydropDown.get(all).click();
				}
			} catch (Exception e) {

			}
		}
	}

	/**
	 * This Method Utility is written to use Implicit wait
	 * 
	 * @param time
	 * @param unit
	 */
	public void waitImplicitWait(int time, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(time, unit);
	}

	/**
	 * This method is written to use Explicit to Satisfy Expected condition of
	 * element to be CliCkable
	 * 
	 * @param timeOutInSeconds
	 * @param locator
	 * @return WebElement locator
	 */
	public WebElement explicitWaitElementToBeClickable(int timeOutInSeconds, By locator) {
		eWait = new WebDriverWait(driver, timeOutInSeconds);
		WebElement element = eWait.until(ExpectedConditions.elementToBeClickable(doFindElement(locator)));
		return element;
	}

	/**
	 * This method is written for Explicit wait to check for WebPage title contains
	 * String.
	 * 
	 * @param timeOutInSeconds
	 * @param Title
	 * @return String title
	 */
	public String explicitWaitTitleContains(int timeOutInSeconds, String title) {
		eWait = new WebDriverWait(driver, timeOutInSeconds);
		eWait.until(ExpectedConditions.titleContains(title));
		return title;
	}

	/**
	 * This method just checks the dom to see if it can locate an element no matter
	 * its visibility.
	 * 
	 * @param timeOutInSeconds
	 * @param locator
	 * @return
	 */
	public WebElement explicitWaitPresenceOfElementLocated(int timeOutInSeconds, By locator) {
		eWait = new WebDriverWait(driver, timeOutInSeconds);
		eWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return doFindElement(locator);
	}

	/**
	 * This method just checks to see if the element is present and also visible. To
	 * check visibility it makes sure the element has a height and width greater
	 * than 0.
	 * 
	 * @param timeOutInSeconds
	 * @param locator
	 * @return
	 */
	public WebElement explicitWaitVisibilityOfElementLocated(int timeOutInSeconds, By locator) {
		eWait = new WebDriverWait(driver, timeOutInSeconds);
		WebElement element = eWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	/**
	 * Custom Wait created for 1sec it will loop to load webPage element to be
	 * visible.
	 * 
	 * @param timeout
	 * @param locator
	 * @return element
	 */
	public WebElement customWait(int timeout, By locator) {
		WebElement element = null;
		for (int i = 0; i < timeout; i++) {
			try {
				driver.findElement(locator);
				break;
			} catch (Exception e) {
				System.out.println("Waiting for element to be visible for " + i + " sec");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
		}
		return element;
	}

	/**
	 * Custom Wait created for 1sec it will loop to check if element is displayed.
	 * 
	 * @param timeout
	 * @param locator
	 * @return element
	 */
	public WebElement customWaitIsElementDisplayed(int timeout, By locator) {
		WebElement element = null;
		for (int i = 0; i < timeout; i++) {
			try {
				driver.findElement(locator);
				element.isDisplayed();
				break;
			} catch (Exception e) {
				System.out.println("Waiting for element to be visible for " + i + " sec");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
		}
		return element;
	}

	/**
	 * This method is written using Fluent wait one of Explicit wait type and here
	 * Anonymous class/Inner Class concept is Implemented to implement Function
	 * Interface which takes WebDriver as input and return WebElement.
	 * 
	 * @param timeout
	 * @param polling
	 * @param locator
	 * @return WebElement
	 */
	public WebElement fluentWait(int timeout, int polling, final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling)).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}

	/**
	 * Fluent wait using Expected Conditions to check if Element is visible
	 * 
	 * @param timeout
	 * @param polling
	 * @param locator
	 * @return
	 */
	public WebElement fluentWaitEC(int timeout, int polling, By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(polling)).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	/**
	 * 
	 * @param time
	 * @param locator
	 */
	public void explicitWaitElementTobeSelected(int timeOutInSeconds, By locator) {
		eWait = new WebDriverWait(driver, timeOutInSeconds);
		eWait.until(ExpectedConditions.elementToBeSelected(doFindElement(locator)));
	}

	/**
	 * This method is written to drag and drop element using Selenium Actions method
	 * dragAndDrop
	 * 
	 * @param source
	 * @param target
	 */
	public void doAction(By source, By target) {
		action = new Actions(driver);
		action.dragAndDrop(doFindElement(source), doFindElement(target)).build().perform();
	}

	/**
	 * This Action method is written to clickAndHold WebElement button
	 * 
	 * @param locator
	 */
	public void doActionClickHold(By locator) {
		action = new Actions(driver);
		action.clickAndHold(doFindElement(locator)).build().perform();
	}

	/**
	 * This Action class Method is written to doubleClick on button.
	 * 
	 * @param locator
	 */
	public void doDoubleClick(By locator) {
		action = new Actions(driver);
		action.doubleClick(doFindElement(locator)).build().perform();
	}

	/**
	 * This Actions class method is written to click on button and capture multiple
	 * options then matching value provided will be clicked.
	 * 
	 * @param locator
	 * @param optionList
	 * @param value
	 */
	public void doContextClickClickElement(By locator, List<WebElement> optionList, String value) {
		action = new Actions(driver);
		ElementUtil eu = new ElementUtil(driver);
		action.contextClick(eu.doFindElement(locator)).build().perform();
		for (int i = 0; i < optionList.size(); i++) {
			String text = optionList.get(i).getText();
			if (text.equals(value)) {
				optionList.get(i).click();
				break;
			}
		}

	}

	/**
	 * This Custom method created using Actions class to capture value of Context
	 * button on clicking and stores in List<String>
	 * 
	 * @param locator
	 * @param optionList
	 * @return List<String> value of context button
	 */
	public List<String> doContextClickCaptureText(By locator, List<WebElement> optionList) {
		List<String> ar = new ArrayList<String>();
		action = new Actions(driver);
		action.contextClick(doFindElement(locator)).build().perform();
		System.out.println("Total rightclick options:-" + optionList.size());
		for (int i = 0; i < optionList.size(); i++) {
			String text = optionList.get(i).getText();
			ar.add(text);
		}
		return ar;
	}

}
