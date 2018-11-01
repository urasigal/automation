package com.zixi.reports;

import org.testng.ISuite;
import org.testng.ITestResult;

import com.zixi.zapi.ZapiExecutionProps;

public class SuiteListenerZapiReporterAdapter extends SuiteListenerZapiReporter {
	private boolean execStatus = true;
	private StringBuffer testFlowDescription = null;
	private int testStepCnt = 1;
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		testFlowDescription.append( testStepCnt + ") Test step name: " + result.getTestName());
		testStepCnt++;
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		testFlowDescription.append(" - status passed\n");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		testFlowDescription.append(" - status failed\n");
		execStatus = false;
	}
	
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		testFlowDescription.append("Test case flow (scenario):\n");	
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
