package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterDeleteFromFileDriver;

public class BroadcasterDeleteFromFilesTest  extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {testDriver = new BroadcasterDeleteFromFileDriver(); }

	@Parameters({ "userName","userPass","login_ip", "uiport", "path", "user", "pass", "testid"})
	@Test
	public void broadcasterDeleteFromFilesInventory(String userName, String userPass, String login_ip, String uiport, String path, String user, String pass, String testid)
	throws Exception {
		
		 productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		sutProcessId 	= BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] { "userName","userPass","login_ip", "uiport", "path", "user", "pass", "testid"}, 
		new String[] { userName, userPass, login_ip, uiport, path, user, pass, testid });
		
		driverReslut 	= ((BroadcasterDeleteFromFileDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, path, user, pass);
		
		Assert.assertEquals(driverReslut.getResult(),"{}");
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster"));
	}
	
}