package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ReceiverAddSourceToOutputDriver;


public class ReceiverAddSourceToOutputTest  extends BaseTestZixiMainComponents{

	@BeforeClass
	public void testInit() { testDriver = new ReceiverAddSourceToOutputDriver();}

	@Parameters({ "userName", "userPass", "login_ip", "uiport",  "inputStreamIdArg", "outputStreamIdArg", "fallback", "testid"})
	@Test
	public void addSourceToReceiverOutput(String userName, String userPass, String login_ip, String uiport, String inputStreamIdArg, String outputStreamIdArg, String fallback, String testid)
	throws Exception {
		
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport",  "inputStreamIdArg", "outputStreamIdArg", "fallback", "testid" }, 
				
		new String[] { userName, userPass, login_ip, uiport,  inputStreamIdArg,  outputStreamIdArg, fallback, testid });
			
		// String streamForDeletion = ((ReceiverPullInputStreamDeletionDriver) testDriver).testIMPL(userName, userPassword, login_ip, uiport, id);
		
		driverReslut = ((ReceiverAddSourceToOutputDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport,  inputStreamIdArg,  outputStreamIdArg, fallback);
		Assert.assertEquals(driverReslut.getResult(), "Input assigned to output stream.");
	}
}
