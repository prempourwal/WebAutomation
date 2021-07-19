package com.prem.factory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.prem.helper.PropertiesHelper;

public class DriverFactory {

	PropertiesHelper prop = new PropertiesHelper();
	WebDriver driver;

	public WebDriver getDriver() throws InterruptedException, IOException {

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

			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);

			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingDriverExecutable(
							new File(System.getProperty("user.dir") + "/src/test/resources/driver/chromedriver.exe"))
					.usingAnyFreePort().build();

			options.merge(cap);
			driver = new ChromeDriver(service, options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
		}
		return driver;
	}
}
