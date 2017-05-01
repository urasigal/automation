package com.zixi.testing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.util.TestLinkAPIException;

import com.zixi.drivers.drivers.*;
import com.zixi.drivers.setup.SetSutUpTimeDriver;
import com.zixi.drivers.tools.DriverReslut;
import com.zixi.email.drivers.GoogleMailDriver;
import com.zixi.tools.TestlinkIntegration;

/*
 * This is a most high hierarchy test class. All test case classes have to inherit from this class.
 */

public class BaseTest {
	
	// It is an interface, all test drivers have to implement this interface.
	protected TestDriver 						testDriver; //Reference to test driver interface. `
	
	protected GoogleMailDriver mail;
	// Check SUT up time.
	protected SetSutUpTimeDriver 				setSutUpTimeDriver;
	protected String 							res                         =   "";
	protected boolean 							crashFlag;
	protected String 							testid; // Stores unique test number which is the number provided by TestLink test management system. 
	protected String 							version						= 	""; // Stores the SUT version (feeder broadcaster receiver).
	protected String 							automationTestIdentifiers 	= 	""; // Stores an qutomation test name and automation suite name, then it will be written to TestLinlk. 
	protected ProductAboutDriver 				productAboutDriver 			= 	new ProductAboutDriver();
	protected TestBaseFunction 					testBaseFunction 			= 	new TestBaseFunction ();
	protected StringBuffer 						testFlowDescriptor 			=	new StringBuffer("Test flow: "); // Put it any place in order to describe a test flow.
	protected String 							sutProcessId;
	protected double 							testDuration;
	
	//Writes test results to the TestLink.
	protected String 							testLinkTestParameters 		= 	"";
	
	// logging stuff - uses all test cases to write a test process execution log. This log is intended to be used by a test automation developers.
	protected static  Logger       				LOGGER      				= 	null;
	protected static  FileHandler  				FILEHANDLER 				= 	null ;
	protected static  StreamHandler				STREAMHANDLER				= 	null;
	
	protected Object 							params[];
	protected String 							manulDescription 			= 	"";
	
	protected DriverReslut						driverReslut				= 	null;		
	
	@BeforeTest
	public void startTest(final ITestContext testContext) {
		automationTestIdentifiers = "Test name is: " + testContext.getName() + "\nSuite name is: " + testContext.getSuite().getName();
	}
	
	//@BeforeMethod: The annotated method will be run before each test method. 
	@Parameters({"testid"})
	@BeforeMethod
	public void beforeTes(String testid) throws TestLinkAPIException, IOException
	{
		setSutUpTimeDriver = new SetSutUpTimeDriver();
		testDuration = System.currentTimeMillis();
		LOGGER = getLoggerInstance(); 
		this.testid = testid;
		System.out.println(this.getClass().getName());
		testDriver.setLogger(LOGGER); // Provide logger instance 
	}
	
	// Write test results to the TestLink.
	@AfterMethod
    public void afterTest(Method test, ITestResult result) 
	{
	 testDuration = System.currentTimeMillis() - testDuration;
	 LOGGER.entering(this.getClass().getName(), "afterTest");
     String crashStatus = "";
    
     TestlinkIntegration tl = new TestlinkIntegration();
	 try
     {	
		 res = setSutUpTimeDriver.continuousUpTimeCheck();
			
			if(res.equals("pass"))
			{
				crashFlag = false;
			}
			else
			{
				crashFlag = true;
			}
		 
        if(crashFlag)
        {
       	  crashStatus = "There was a crash in the recent tests " + res;
	      LOGGER.info("Test duration[ms]: " + testDuration);
	    	
          tl.setResult(testid, ExecutionStatus.FAILED,  this.getClass().getCanonicalName() + "\n" + productAboutDriver.version + "\n" +  
          automationTestIdentifiers + "\nTest Parameters: "+ testLinkTestParameters + " Manul description: " + manulDescription + testFlowDescriptor + 
          "\nTest duration[ms]: " + testDuration + "\n" + "Test notes " + driverReslut.touchResutlDescription(" ") + "\n" + crashStatus, getBuildIdFromFile());
         
          String message = "Crash detected \n";
          message =  message + tl.getTestInfo(Integer.parseInt(testid)).toString() + "\n" + "Test Parameters: "+ testLinkTestParameters;
          String subject = "Crash detected";
          GoogleMailDriver.sendToList(SetSutUpTimeDriver.getEmailAddressesFromSystemFolder("src/main/resources/email_addresses"), subject, message);
        }
        else
        {	
	        if (result.isSuccess()) 
	        {
	        	LOGGER.info("Test duration[ms]: " + testDuration);
	        	
	            tl.setResult(testid, ExecutionStatus.PASSED, this.getClass().getCanonicalName() + "\n" + productAboutDriver.version + "\n"+  
	            automationTestIdentifiers + "\nTest Parameters: "+ testLinkTestParameters  + "\nManul description: " + manulDescription  + testFlowDescriptor +
	            "\nTest duration[ms]: " + testDuration + "\n " + "Test notes " + driverReslut.touchResutlDescription(" ") + "\n" + crashStatus, getBuildIdFromFile()); // pass data to a testLink notes in test execution.
	        } 
	        else 
	        {
	        	LOGGER.info("Test duration[ms]: " + testDuration);
	        	
	            tl.setResult(testid,ExecutionStatus.FAILED,  this.getClass().getCanonicalName() + "\n" + productAboutDriver.version + "\n" +  
	            automationTestIdentifiers + "\nTest Parameters: "+ testLinkTestParameters + " Manul description: " + manulDescription + testFlowDescriptor + 
	            "\nTest duration[ms]: " + testDuration + "\n" + "\n Error is " + result.getThrowable().getMessage() + "\n Exception stack trace: " + 
	            result.getThrowable().getStackTrace()  + "Test notes " + driverReslut.touchResutlDescription(" ") + "\n" + crashStatus, getBuildIdFromFile());
	            
	            String message = "Test failed \n";
	            message =  message + tl.getTestInfo(Integer.parseInt(testid)).toString() + "\n" + "Test Parameters: "+ testLinkTestParameters;
	            String subject = "Test failed \n";
	            GoogleMailDriver.sendToList(SetSutUpTimeDriver.getEmailAddressesFromSystemFolder("src/main/resources/email_addresses"), subject, message);
	        }
       }
        
        setSutUpTimeDriver.uptimeSet("src/main/resources/uptime", "src/main/resources/current_uptime");
     }
     catch(Exception e)
     {
    	 System.out.println("The error is " + e.getMessage()); 
     }
     LOGGER.exiting(getClass().getName(), "afterTest");
    }
	
	protected String  buildTestParametersString(String parametersNmes[], String[] paramertersValues)
	{
		StringBuffer sb = new StringBuffer();
		int length = parametersNmes.length;
		for(int i = 0 ; i < length; i++ )
		{
			sb.append("\n").append(parametersNmes[i]).append(" = ").append(paramertersValues[i]); 
		}
		testLinkTestParameters =  sb.toString();
		return testLinkTestParameters;
	}
	
	protected class TestBaseFunction {
		protected String buildTestParametersString(String parametersNmes[], String[] paramertersValues)
		{
			StringBuffer sb 		= 	new StringBuffer();
			int 		 length 	= 	parametersNmes.length;
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
			  	PrintWriter pw 			= new PrintWriter("src/main/resources/log");
			  	pw.close();
			    BaseTest.FILEHANDLER 	= 	new FileHandler("src/main/resources/log", true);
			    BaseTest.STREAMHANDLER	= 	new StreamHandler(System.out, new SimpleFormatter());
			    BaseTest.LOGGER 		= 	Logger.getLogger("com");
			    BaseTest.FILEHANDLER.setFormatter(new SimpleFormatter());
				BaseTest.LOGGER.addHandler(BaseTest.FILEHANDLER);
				BaseTest.LOGGER.addHandler(BaseTest.STREAMHANDLER);
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
	
	// Get TestLink test plan ID.
	private int getBuildIdFromFile() throws IOException{
		
		String 	line;
		int 	buildId = -1;
		try (
		    InputStream 	  fis 	= 	new FileInputStream("src/main/resources/build");
		    InputStreamReader isr 	= 	new InputStreamReader(fis, Charset.forName("UTF-8"));
		    BufferedReader	  br  	= 	new BufferedReader(isr);
		) {
		    while ((line = br.readLine()) != null) {
		    	buildId = Integer.parseInt(line);
		    }
		}
		return buildId;
	}
}
