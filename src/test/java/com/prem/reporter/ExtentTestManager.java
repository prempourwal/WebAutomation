package com.prem.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	private static ExtentReports extent = new ExtentReports();
	static ExtentTest test;

	public static ExtentTest getTest() {
		return test;
	}

	public static void createTest(String testName, String Desc) {
		test = extent.createTest(testName, Desc);
	}
}
