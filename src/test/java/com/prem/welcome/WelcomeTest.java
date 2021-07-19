package com.prem.welcome;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import com.prem.Pages.WelcomePage;
import com.prem.base.TestBase;

public class WelcomeTest extends TestBase{
    WelcomePage welcome = getWelcomePage();
	
	@After
	public void tearDown() throws Exception {
		welcome.tearDown();
	}

	@Test
	public void welcome_test() throws IOException {
		welcome.openWebPage(getPropertiesHelper().getPropValues("app.url"));
		welcome.enterEmailId("prem_automation@gmail.com");
		
	}

}
