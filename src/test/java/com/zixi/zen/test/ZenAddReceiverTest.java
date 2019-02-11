package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddReceiverDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddReceiverTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddReceiverDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "receiverName", "receiverUser", 
	"receiverPass", "shhKeyName", "accessTagName", "testid"})
	@Test
	public void addFeederToZen(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport,
	String receiverName, String receiverUser, String receiverPass, String shhKeyName, String accessTagName, 
	String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] { "zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "receiverName", "receiverUser", "receiverPass", "shhKeyName", "accessTagName", "testid"}, 
		new String[] { zenUserName, zenUserPass, zenLogin_ip, zenUiport, receiverName, receiverUser, receiverPass, shhKeyName, accessTagName, testid });
		driverReslut = ((ZenAddReceiverDriver) testDriver).addReceiver(zenUserName, zenUserPass, zenLogin_ip, zenUiport, receiverName, receiverUser, receiverPass, shhKeyName, accessTagName);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}