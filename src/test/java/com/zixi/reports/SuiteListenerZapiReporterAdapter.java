package com.zixi.reports;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.testng.ISuite;
import org.testng.ITestResult;

import com.zixi.zapi.ZapiCycleIntegrator;
import com.zixi.zapi.ZapiExecutionProps;

public class SuiteListenerZapiReporterAdapter extends SuiteListenerZapiReporter {
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
		result.getTestContext().getName() + " Status: Failed. " + result.getThrowable().getMessage());
		execStatus = false;
	}
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		testFlowDescription.append("Test flow: ");	
	}
	
	@Override
	public void onFinish(ISuite suite) {
		String status       	= null;
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
			String line;
			try (InputStream fis = new FileInputStream("src/main/resources/cycleid");
				InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
				BufferedReader br = new BufferedReader(isr);) {
			while ((line = br.readLine()) != null) {
				cycleId = line;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("SuiteListenerZapiReporterAdapter.onFinish()");
			}
		}
		if(folderId.equals(""))
		{
		try {
				folderId = ZapiCycleIntegrator.getFolderIdFromCycle(zapiUser, zapiUser, zapiUser, zapiUser, zapiUser, zapiUser, zapiUser);
			} catch (URISyntaxException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			if(execStatus == true)
				status = "1"; // Passed.
			else status = "2"; // Passed.
			ZapiExecutionProps.createNewTestExecutionWithStatus_TestCycle_TestFolder(status, projectId, issueId, cycleId, folderId, 
			versionId, assigneeType, zapiUser, zapiAccesskey, zapiSecretkey, testFlowDescription.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
