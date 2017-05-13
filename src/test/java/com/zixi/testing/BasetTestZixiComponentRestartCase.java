package com.zixi.testing;


import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.zixi.drivers.setup.SetSutUpTimeDriver;
import com.zixi.email.drivers.GoogleMailDriver;
import com.zixi.tools.TestlinkIntegration;

public class BasetTestZixiComponentRestartCase extends BaseTestZixiMainComponents{
	// Write test results to the TestLink. It is only overwritten method from a base class. 
	@AfterMethod
    public void afterTest(Method test, ITestResult result) 
	{
	 testDuration = System.currentTimeMillis() - testDuration;
	 LOGGER.entering(this.getClass().getName(), "afterTest");
    
     TestlinkIntegration tl = new TestlinkIntegration();
	 try{	
	        if (result.isSuccess()) 
	        {
	        	LOGGER.info("Test duration[ms]: " + testDuration);
	            tl.setResult(testid, ExecutionStatus.PASSED, this.getClass().getCanonicalName() + "\n" + productAboutDriver.version + "\n"+  
	            automationTestIdentifiers + "\nTest Parameters: "+ testLinkTestParameters  + "\nManul description: " + manulDescription  + testFlowDescriptor +
	            "\nTest duration[ms]: " + testDuration + "\n " + "Test notes " + driverReslut.touchResutlDescription(" ") + "\n" , getBuildIdFromFile()); // pass data to a testLink notes in test execution.
	        } 
	        else 
	        {
	        	LOGGER.info("Test duration[ms]: " + testDuration);
	            tl.setResult(testid,ExecutionStatus.FAILED,  this.getClass().getCanonicalName() + "\n" + productAboutDriver.version + "\n" +  
	            automationTestIdentifiers + "\nTest Parameters: "+ testLinkTestParameters + " Manul description: " + manulDescription + testFlowDescriptor + 
	            "\nTest duration[ms]: " + testDuration + "\n" + "\n Error is " + result.getThrowable().getMessage() + "\n Exception stack trace: " + 
	            result.getThrowable().getStackTrace()  + "Test notes " + driverReslut.touchResutlDescription(" ") + "\n", getBuildIdFromFile());
	            
	            String message = "Test failed \n";
	            message =  message + tl.getTestInfo(Integer.parseInt(testid)).toString() + "\n" + "Test Parameters: "+ testLinkTestParameters;
	            String subject = "Test failed \n";
	            GoogleMailDriver.sendToList(SetSutUpTimeDriver.getEmailAddressesFromSystemFolder("src/main/resources/email_addresses"), subject, message);
	    }
        setSutUpTimeDriver.uptimeSet("src/main/resources/uptime", "src/main/resources/current_uptime");
     }
     catch(Exception e)
     {
    	 System.out.println("The error is " + e.getMessage()); 
     }
     LOGGER.exiting(getClass().getName(), "afterTest");
    }
}
