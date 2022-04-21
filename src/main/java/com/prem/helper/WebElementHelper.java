package com.prem.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.prem.elements.XpathType;

public class WebElementHelper {

	ThreadLocal<WebDriver> driver;
	WebDriverWait wait;
	XpathType xpathType;

	protected static final Logger LOG = Logger.getLogger(WebElementHelper.class);

	public WebElementHelper(ThreadLocal<WebDriver> webDriver) {
		this.driver = webDriver;
	}

	public void click(String element) {
		LOG.info("click on element : ".concat(element));
		driver.get().findElement(By.xpath(element));
	}

	public void sendKeys(XpathType typeOfXpath, String elementXpath, String value) {
		LOG.info("sending keys as : ".concat(value));
		driver.get().findElement(By.id(elementXpath)).click();
		driver.get().findElement(By.id(elementXpath)).clear();
		sendKey(XpathType.ID, elementXpath, value);

	}

	private void sendKey(XpathType xPathType, String webElement, String text) {
		this.xpathType = xPathType;
		switch (xPathType) {
		case ID:
			driver.get().findElement(By.id(webElement)).sendKeys(text);
			break;
		case XPATH:
			driver.get().findElement(By.xpath(webElement)).sendKeys(text);
			break;
		case NAME:
			driver.get().findElement(By.name(webElement)).sendKeys(text);
			break;
		default:
			LOG.info("Element type not found.!!!!");
			break;
		}
	}

}
