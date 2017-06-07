package com.zixi.reports;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestListenerLogger extends TestListenerAdapter {
	
	public void onTestFailure(ITestResult tr)
	{
		log("Test failed......................................................");
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		log("Test skipped......................................................");
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		log("Test passed......................................................");
	}
	
	private void log(String string)
	{
		System.out.println(string);
	}
}
