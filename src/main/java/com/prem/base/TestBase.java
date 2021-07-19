package com.prem.base;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.prem.Pages.WelcomePage;
import com.prem.factory.DriverFactory;
import com.prem.helper.PropertiesHelper;
import com.prem.helper.WebElementHelper;

public class TestBase {
	
	private WebDriver driver;
	private WelcomePage welcomePage;
	private PropertiesHelper propertiesHelper;
	
	WebElementHelper elementHelper;
	
	protected static final Logger LOG = Logger.getLogger(TestBase.class);
	
	public TestBase() {
		try {
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
		if(!(welcomePage instanceof WelcomePage)) {
			welcomePage=new WelcomePage(driver, elementHelper);
		}
	}
	
	public WelcomePage getWelcomePage() {
		return welcomePage;
	}
	
	public PropertiesHelper getPropertiesHelper() {
		return propertiesHelper;
	}

}
