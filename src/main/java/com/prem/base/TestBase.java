package com.prem.base;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import com.prem.Pages.WelcomePage;
import com.prem.factory.DriverFactory;
import com.prem.helper.Constants;
import com.prem.helper.PropertiesHelper;
import com.prem.helper.WebElementHelper;

public class TestBase {

	private ThreadLocal<WebDriver> driver;
	private WelcomePage welcomePage;
	private PropertiesHelper propertiesHelper;
	WebElementHelper elementHelper;

	protected static final Logger LOG = Logger.getLogger(TestBase.class);

	public TestBase() {
		try {
			BasicConfigurator.configure();
			this.driver = new DriverFactory().getDriver();
			this.elementHelper = new WebElementHelper(driver);
			this.propertiesHelper = new PropertiesHelper();
			initPages();

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

//initializing object of pages
	private void initPages() {
		if (!(welcomePage instanceof WelcomePage)) {
			welcomePage = new WelcomePage(driver, elementHelper);
		}
	}

	public WelcomePage getWelcomePage() {
		return welcomePage;
	}

	public PropertiesHelper getPropertiesHelper() {
		return propertiesHelper;
	}

	public void openNewTab(String newUrl) {
		Constants.originalTab = driver.get().getWindowHandle();
		driver.get().switchTo().newWindow(WindowType.TAB);
		driver.get().get(newUrl);
	}

	public void OpenPreviousTab() {
		System.out.println("Constants.originalTab" + Constants.originalTab);
		for (String windowHandle : driver.get().getWindowHandles()) {
			if (Constants.originalTab.contentEquals(windowHandle)) {
				driver.get().close();
				driver.get().switchTo().window(Constants.originalTab);
				break;
			}
		}
	}

	public void scrollToElements(String elementXpath) throws InterruptedException {
		WebElement element = driver.get().findElement(By.xpath(elementXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public void openPage(String url) {
		driver.get().get(url);
	}
}
