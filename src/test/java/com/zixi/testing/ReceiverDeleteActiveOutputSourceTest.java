package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.ReceiverAddSourceToOutputDriver;
import com.zixi.drivers.drivers.ReceiverDeleteActiveOutputSourceDriver;

public class ReceiverDeleteActiveOutputSourceTest  extends BaseTestZixiMainComponents{

	@BeforeClass
	public void testInit() { testDriver = new ReceiverDeleteActiveOutputSourceDriver();}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "outputStreamIdArg", "remoteBX_UI_port", "testid"})
	@Test
	public void addSourceToReceiverOutput(String userName, String userPass, String login_ip, String uiport, String outputStreamIdArg, String remoteBX_UI_port, String testid)
	throws Exception {
		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "outputStreamIdArg", "remoteBX_UI_port", "testid" }, 
		new String[] { userName, userPass, login_ip, uiport, outputStreamIdArg, remoteBX_UI_port, testid });												  
		driverReslut = ((ReceiverDeleteActiveOutputSourceDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, outputStreamIdArg, remoteBX_UI_port);
		System.out.println(driverReslut.getResult());
	}
}
