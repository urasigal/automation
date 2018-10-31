package com.zixi.reports;

import org.testng.ISuite;

import com.zixi.zapi.ZapiExecutionProps;

public class SuiteListenerZapiReporterAdapter extends SuiteListenerZapiReporter {
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
			versionId, assigneeType, zapiUser, zapiAccesskey, zapiSecretkey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
