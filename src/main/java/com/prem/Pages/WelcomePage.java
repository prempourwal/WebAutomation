package com.prem.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.prem.elements.WelcomePageElements;
import com.prem.elements.XpathType;
import com.prem.helper.CommonPage;
import com.prem.helper.WebElementHelper;

public class WelcomePage extends WelcomePageElements {

	WebDriver driver;
	WebElementHelper elementHelper;
	XpathType xpathType;
	public CommonPage commonPage;

	public WelcomePage(ThreadLocal<WebDriver> webDriver, WebElementHelper helper) {
		this.driver = webDriver.get();
		this.elementHelper = helper;
		PageFactory.initElements(webDriver.get(), helper);
	}

	public void enterEmailId(String emailId) {
		elementHelper.sendKeys(XpathType.ID, loginEmailId, emailId);
	}

	public void tearDown() {
		driver.quit();
	}
}
