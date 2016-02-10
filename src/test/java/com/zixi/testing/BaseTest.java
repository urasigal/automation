package com.zixi.testing;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.*;
import com.zixi.tools.TestlinkIntegration;

public class BaseTest {
	protected TestDriver testDriver;
	protected ClassLoader classLoader;
	protected Object driverObj;
	protected Method m;
	protected String testid;
	protected String version = "";
	protected String automationTestIdentifiers = "";
	protected ProductAboutDriver productAboutDriver = new ProductAboutDriver();
	
	protected String testParameters =  "";
	
	// Reflection stuff
	protected Class c;
	protected Object params[];
	
	@BeforeTest
	public void startTest(final ITestContext testContext) {
		automationTestIdentifiers = "Test name is: " + testContext.getName() + "\nSuite name is: " + testContext.getSuite().getName() ;
	}
	
	@Parameters({"testid"})
	@BeforeMethod
	public void beforeTes(String testid) throws MalformedURLException
	{
		this.testid = testid;
		System.out.println(this.getClass().getName());
		TestlinkIntegration tl = new TestlinkIntegration();
		tl.setResult(testid,ExecutionStatus.BLOCKED,this.getClass().getCanonicalName());
	}
	
	@AfterMethod
    public void afterTest(Method test, ITestResult result)
            throws MalformedURLException {
		
        TestlinkIntegration tl = new TestlinkIntegration();

        if (result.isSuccess()) {
 
            tl.setResult(testid,
                    ExecutionStatus.PASSED, this.getClass().getCanonicalName() + "\n" + version + "\n"+  
            automationTestIdentifiers + "\nTest Parameters: "+ testParameters);
        } else {
            tl.setResult(testid,
                    ExecutionStatus.FAILED,  this.getClass().getCanonicalName() + "\n" + version + "\n"+  
                            automationTestIdentifiers + "\nTest Parameters: "+ testParameters);
        }
    }
	
	protected String buildTestParametersString(String parametersNmes[], String[] paramertersValues)
	{
		StringBuffer sb = new StringBuffer();
		int length = parametersNmes.length;
		for(int i = 0 ; i < length; i++ )
		{
			sb.append("\n").append(parametersNmes[i]).append(" = ").append(paramertersValues[i]); 
		}
		return sb.toString();
	}
}
