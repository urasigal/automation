package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.tools.DriverReslut;


// This class is used in general purpose of deletion of output stream on a zixi broadcaster server.
public class BroadcaserSingleOutputStreamDeletionTest extends BaseTest{
	
	@BeforeClass
	public void testInit() { testDriver = new BroadcaserSingleOutputStreamDeletionDriver(); } // Super class element

	// Test parameters.
	@Parameters({ "login_ip","userName","userPassword","id","uiport", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String login_ip,String userName,String userPassword,String id, String uiport, String testid) throws Exception 
	{
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		
		testParameters = buildTestParametersString(new String[] { "login_ip","userName","userPassword", "id","uiport", "testid" }, 
		new String[] {login_ip,userName,userPassword,id,uiport, testid});
		
		driverReslut = ((BroadcaserSingleOutputStreamDeletionDriver) testDriver).testIMPL(login_ip, userName, userPassword, id, uiport);
		
		Assert.assertEquals(driverReslut.getResult(), "Output " +  id + " removed.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
