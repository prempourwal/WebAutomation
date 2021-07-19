package com.prem.runner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.prem.welcome.WelcomeTest;

@RunWith(Suite.class)
@SuiteClasses({
	WelcomeTest.class
})
public class TestRunner {
	
	@BeforeClass
	public static void Setup() {
		Logger log = Logger.getLogger(TestRunner.class.getName());
		PropertyConfigurator.configure(System.getProperty("user.dir").concat("/src/test/resources/config/log4j.properties"));
		log.info("********* Starting Test Execution. ***********");
	}
}
