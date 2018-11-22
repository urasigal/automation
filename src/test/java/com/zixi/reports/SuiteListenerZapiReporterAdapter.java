package com.zixi.reports;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.zixi.drivers.drivers.FeederPostKeyDriver;
import com.zixi.zapi.ZapiCycleIntegrator;
import com.zixi.zapi.ZapiExecutionProps;

public class SuiteListenerZapiReporterAdapter implements ISuiteListener, ITestListener{
	private boolean execStatus = true;
	private StringBuffer testFlowDescription = new StringBuffer();
	private int testStepCnt = 1;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		testStepCnt++;
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		testFlowDescription.append("Test suite: " + result.getTestContext().getSuite().getName() + ", Step seq num: " + testStepCnt + " " +
		result.getTestContext().getName() + " Status: Failed. " + result.getThrowable().getMessage().replaceAll("[^a-zA-Z0-9]", ""));
		execStatus = false;
	}
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		testFlowDescription.append("Test flow: ");	
	}
	
	@Override
	public void onFinish(ISuite suite) {
		String status       	= "";
		String projectId		= suite.getParameter("projectId");
		String issueId			= suite.getParameter("issueId");
		String cycleId			= suite.getParameter("cycleId");
		String versionId		= suite.getParameter("versionId");
		String assigneeType		= suite.getParameter("assigneeType");
		String zapiAccesskey	= suite.getParameter("zapiAccesskey");
		String zapiSecretkey	= suite.getParameter("zapiSecretkey");
		String folderId			= suite.getParameter("folderId");
		String zapiUser			= suite.getParameter("zapiUser");
		
		if(cycleId.equals("")) {
			
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
