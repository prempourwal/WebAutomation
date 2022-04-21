package com.prem.welcome;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.prem.Pages.WelcomePage;
import com.prem.base.TestBase;
import com.prem.reporter.ExtentTestManager;

public class LoginTest extends TestBase {
	WelcomePage welcome = getWelcomePage();

	@Test
	public void Test01() throws IOException {
		ExtentTestManager.getTest().info("Site Title - " + getPropertiesHelper().getPropValues("app.url"));
		openPage(getPropertiesHelper().getPropValues("app.url"));
		welcome.enterEmailId(getPropertiesHelper().getPropValues("admin.user"));
		ExtentTestManager.getTest().info("Login as admin");
	}

	@AfterClass
	public void afterClass() {
		welcome.tearDown();
	}

}
