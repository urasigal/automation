package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.tools.DriverReslut;


// This class is used in general purpose of deletion of output stream on a zixi broadcaster server.
public class BroadcaserSingleOutputStreamDeletionTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new BroadcaserSingleOutputStreamDeletionDriver(); } // Super class element

	// Test parameters.
	@Parameters({ "login_ip","userName","userPassword","id","uiport", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String login_ip,String userName,String userPassword,String id, String uiport, String testid) throws Exception 
	{
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		String memOnStart = null;
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");

		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		
		buildTestParametersString(new String[] { "login_ip","userName","userPassword", "id","uiport", "testid" }, 
		new String[] {login_ip,userName,userPassword,id,uiport, testid});
		
		driverReslut = ((BroadcaserSingleOutputStreamDeletionDriver) testDriver).testIMPL(login_ip, userName, userPassword, id, uiport);
		
		String 		memOnEnd = null;
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "Output " +  id + " removed.");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
