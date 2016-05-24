package com.zixi.testing;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;

import com.zixi.drivers.*;
import com.zixi.tools.TestlinkIntegration;

/*
 * This is a most high hierarchy test class in all automation test project. All test case classes must to inherit from this class.
 */

public class BaseTest {
	
	// It is an interface all test drivers have to implement this interface.
	protected TestDriver testDriver;
	
	protected ClassLoader classLoader;
	protected Object driverObj;
	protected Method m;
	protected String testid;
	protected String version = "";
	protected String automationTestIdentifiers = "";
	protected ProductAboutDriver productAboutDriver = new ProductAboutDriver();
	protected TestBaseFunction testBaseFunction = new TestBaseFunction ();
	
	protected String pid;
	
	// Writes test results to the TestLink.
	protected String testParameters =  "";
	
	// logging stuff - uses all test cases to write a test process execution log. This log is intended to be used by a test automation developers.
	protected static  Logger       LOGGER      = null;
	protected static  FileHandler  FILEHANDLER = null ;
	
	// Reflection stuff.
	protected Class c;
	protected Object params[];
	protected String manulDescription = "";
	
	@BeforeTest
	public void startTest(final ITestContext testContext) {
		automationTestIdentifiers = "Test name is: " + testContext.getName() + "\nSuite name is: " + testContext.getSuite().getName() ;
	}
	
	@Parameters({"testid"})
	@BeforeMethod
	public void beforeTes(String testid) throws MalformedURLException
	{
		LOGGER = getLoggerInstance();
		LOGGER.info(getClass().getName()); 
		this.testid = testid;
		System.out.println(this.getClass().getName());
		TestlinkIntegration tl = new TestlinkIntegration();
		tl.setResult(testid,ExecutionStatus.BLOCKED,this.getClass().getCanonicalName());
	}
	
	@AfterMethod
    public void afterTest(Method test, ITestResult result) {
     try{		
        TestlinkIntegration tl = new TestlinkIntegration();
        if (result.isSuccess()) {
            tl.setResult(testid,
                    ExecutionStatus.PASSED, this.getClass().getCanonicalName() + "\n" + version + "\n"+  
            automationTestIdentifiers + "\nTest Parameters: "+ testParameters  + " Manul description: " + manulDescription); // pass data to a testLink notes in test execution.
        } else {
            tl.setResult(testid,
                    ExecutionStatus.FAILED,  this.getClass().getCanonicalName() + "\n" + version + "\n"+  
                            automationTestIdentifiers + "\nTest Parameters: "+ testParameters + " Manul description: " + manulDescription );
        }
     }catch(Exception e)
     {
    	 System.out.println("The error is" + e.getMessage()); 
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
	
	
	protected class TestBaseFunction {
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
	
	// Singleton manner of definition.
	protected Logger getLoggerInstance()
	{
		if(BaseTest.LOGGER == null)
		{
		  try {
			    BaseTest.FILEHANDLER = new FileHandler("src/main/resources/log");
			    BaseTest.LOGGER = Logger.getLogger("com");
			    BaseTest.FILEHANDLER.setFormatter(new SimpleFormatter());
				BaseTest.LOGGER.addHandler(BaseTest.FILEHANDLER);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				System.out.println(" ------------------------------------------- Cant to open a file");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(" -------------------------------------------- Cant to open a file");
			}
		  return BaseTest.LOGGER;
		}
		return BaseTest.LOGGER;
	}
}
