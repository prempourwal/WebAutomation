package com.prem.reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CustomReportFormatter implements ITestListener, IReporter {

	private static ExtentReports extent;
	private static ExtentSparkReporter htmlReport;
	private ExtentTest test;

	protected static final Logger LOG = Logger.getLogger(CustomReportFormatter.class);

	private static final Map<String, String> MIME_TYPES_EXTENSIONS = new HashMap<String, String>() {
		{
			this.put("image/bmp", "bmp");
			this.put("image/gif", "gif");
			this.put("image/jpeg", "jpg");
			this.put("image/jpg", "jpg");
			this.put("image/png", "png");
			this.put("image/svg+xml", "svg");
			this.put("video/ogg", "ogg");
		}
	};

	@Override
	public void onStart(ITestContext contextStart) {
		htmlReport = new ExtentSparkReporter(
				"C:/Users/Prem Prakash/Documents/Eclipse/Workspace/WebAutomation/test-output/ExtentReport.html");
		try {
			htmlReport.loadXMLConfig("C:/Users/Prem Prakash/Documents/Eclipse/Workspace/WebAutomation/src/test/resources/config/ExtentReportsConfig.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			formatHtmlReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent = new ExtentReports();
		extent.attachReporter(htmlReport);

		LOG.info("Method Started : ".concat(contextStart.getName()));
		test = extent.createTest(contextStart.getName());
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "pavan");
	}

	private void formatHtmlReport() throws IOException {
		htmlReport.config().setDocumentTitle("Test Automation");
		htmlReport.config().setReportName("Prem Prakash");
		htmlReport.config().setTheme(Theme.DARK);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Method failed with certain success percentage" + result.getName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().log(Status.FAIL, "Test failed because of " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Method skipped" + result.getName());
		ExtentTestManager.getTest().log(Status.SKIP, "Test skipped because of " + result.getThrowable());
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTestManager.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
		ExtentTestManager.getTest().info(result.getMethod().getMethodName() + " Started...");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentTestManager.getTest().log(Status.PASS, "Test case Passed");

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
