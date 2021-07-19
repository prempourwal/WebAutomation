package com.prem.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.prem.elements.WelcomePageElements;
import com.prem.helper.WebElementHelper;

public class WelcomePage extends WelcomePageElements{
	
	WebDriver driver;
	WebElementHelper elementHelper;

	public WelcomePage(WebDriver driver , WebElementHelper helper) {
		this.driver = driver;
		this.elementHelper = helper;
		PageFactory.initElements(driver, helper);
	}
	public void openWebPage(String url) {
		driver.get(url);
	}
	public void enterEmailId(String emailId) {
		elementHelper.sendKeys(loginEmailId, emailId);
	}
	public void tearDown() {
		driver.quit();
	}
}
