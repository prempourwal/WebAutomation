package com.prem.factory;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.prem.helper.PropertiesHelper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	PropertiesHelper prop = new PropertiesHelper();
	//WebDriver driver;
	ThreadLocal<WebDriver> driver;

	public ThreadLocal<WebDriver> getDriver() throws InterruptedException, IOException {

		if (prop.getPropValues("browser").equalsIgnoreCase("Chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			options.addArguments("disable-infobars");
			// options.addArguments("--headless");

			HashMap<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("plugins.always_open_pdf_externally", true);
			prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
			prefs.put("download.prompt_for_download", false);

			options.setExperimentalOption("prefs", prefs);
			WebDriverManager.chromedriver().setup();
			driver = new ThreadLocal<>();
			driver.set(new ChromeDriver(options));

			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get().manage().deleteAllCookies();
		}
		return driver;
	}
}
