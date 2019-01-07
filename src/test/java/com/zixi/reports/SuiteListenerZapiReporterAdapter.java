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

import com.zixi.drivers.drivers.FeederPostKeyDriver;
import com.zixi.zapi.ZapiCycleIntegrator;
import com.zixi.zapi.ZapiExecutionProps;

public class SuiteListenerZapiReporterAdapter extends SuiteListenerZapiReporter {
	
	private  final String PASSED = "1";
	private  final String FAILED = "2";
	private boolean execStatus = true;
	private StringBuffer testFlowDescription = new StringBuffer("Automated execution. ");
	private int testStepCnt = 0;
	
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
		result.getTestContext().getName() + " Status: Failed. " + result.getThrowable().getMessage() + " ");
		execStatus = false;
	}
	
	@Override
	public void onStart(ISuite suite) {
		execStatus = true;
	}
	
	@Override
	public void onFinish(ISuite suite) {
		try {
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
		
			// Check input parameter.
			if((cycleId == null) || (cycleId.equals(""))) {
				String line;
				// Get stored cycleId.
				try (InputStream fis = new FileInputStream("src/main/resources/cycleid"); InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
					BufferedReader br = new BufferedReader(isr);) {
				while ((line = br.readLine()) != null) {
					cycleId = line;
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("SuiteListenerZapiReporterAdapter.onFinish()");
				}
			}
			
			try {
				// Get secret keys.
				zapiAccesskey = FeederPostKeyDriver.getStringFromUrl("zapiAccesskey");
				zapiSecretkey = FeederPostKeyDriver.getStringFromUrl("zapiSecretkey");
				folderId = ZapiCycleIntegrator.getFolderIdFromCycle( cycleId, versionId,  projectId,  folderId,  zapiUser, zapiAccesskey,  zapiSecretkey);
			} catch (URISyntaxException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				status = (execStatus == true) ? PASSED : FAILED ;
				zapiAccesskey = FeederPostKeyDriver.getStringFromUrl("zapiAccesskey");
				zapiSecretkey = FeederPostKeyDriver.getStringFromUrl("zapiSecretkey");
				
				// Update Jira execution. 
				ZapiExecutionProps.createNewTestExecutionWithStatus_TestCycle_TestFolder( status, projectId, issueId, cycleId, folderId, 
					versionId, assigneeType, zapiUser, zapiAccesskey, zapiSecretkey, testFlowDescription.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}finally {
			execStatus = true;
			testFlowDescription = new StringBuffer("Automated execution. ");
			testStepCnt = 0;
		}
	}
}
