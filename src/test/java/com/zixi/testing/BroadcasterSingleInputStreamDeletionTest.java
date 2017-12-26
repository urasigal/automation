package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.*;

public class BroadcasterSingleInputStreamDeletionTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterSingleInputStreamDeletionDriver(); }

	@Parameters({ "login_ip", "userName", "userPassword", "streamId", "uiport" ,"testid"})
	@Test
	public void broadcasterInputStreamDeletion(String login_ip, String userName, String userPassword, String streamId, String uiport, String testid) throws Exception {
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster" );
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		String memOnStart = null;
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");

		buildTestParametersString(new String[] { "login_ip", "userName", "userPassword", "streamId", "uiport" ,"testid" }, 
		new String[] {login_ip, userName, userPassword, streamId, uiport ,testid });
		
		driverReslut = ((BroadcasterSingleInputStreamDeletionDriver) testDriver).removeInput(login_ip, userName, userPassword, streamId, uiport);
		String 		memOnEnd = null;
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);	
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}