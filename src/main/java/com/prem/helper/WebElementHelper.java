package com.prem.helper;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementHelper {

	WebDriver driver;
	WebDriverWait wait;
	
	protected static final Logger LOG = Logger.getLogger(WebElementHelper.class);
	
	public WebElementHelper(WebDriver driver) {
		this.driver = driver;
	}

	public void click(String element) {
		LOG.info("click on element : ".concat(element));
		waitForElementClickable(element);
		driver.findElement(By.xpath(element));
	}

	public void sendKeys(String elementXpath, String value) {
		LOG.info("sending keys as : ".concat(value));
		driver.findElement(By.id(elementXpath)).click();
		driver.findElement(By.id(elementXpath)).clear();
		driver.findElement(By.id(elementXpath)).sendKeys(value);

	}

	public void switchToNewtab() {
		Set<String> tabs = driver.getWindowHandles();
		for (String tab : tabs) {
			driver.switchTo().window(tab);
		}
	}

	public void scrollToElements(String elementXpath) throws InterruptedException {
		WebElement element = driver.findElement(By.id(elementXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public void waitForElementVisibel(String elementXpath) {
		wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXpath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void waitForElementClickable(String elementXpath) {
		wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
