package com.prem.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonPage {
	ThreadLocal<WebDriver> driver;
	WebDriverWait wait;
	
	protected static final Logger LOG = Logger.getLogger(WebElementHelper.class);
	
	public CommonPage(ThreadLocal<WebDriver> driver) {
		this.driver = driver;
	}
}
	
