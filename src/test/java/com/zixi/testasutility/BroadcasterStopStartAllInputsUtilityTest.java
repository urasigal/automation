package com.zixi.testasutility;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterStopStartAllInputsUtilityDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class BroadcasterStopStartAllInputsUtilityTest extends BaseTestZixiMainComponents
{
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterStopStartAllInputsUtilityDriver(); }
	
	@Parameters({ "login_ip", "userName", "userPassword", "uiport", "login_ip_remote", "testid"})
	@Test
	public void broadcasrStopStartAllInputs(String login_ip, String userName, String userPassword, String uiport, String login_ip_remote, String testid) throws Exception {
		// Get broadcaster PID in the beginning of the test.
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);	
		buildTestParametersString(new String[] {"login_ip", "userName", "userPassword", "uiport", "testid"}, 
		new String[] { login_ip, userName, userPassword, uiport, testid });
		
		for(int i = 0 ; i < 1000; i ++)
		{
			memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
			String memOnStartRemote = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_remote ,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
			
			driverReslut = ((BroadcasterStopStartAllInputsUtilityDriver) testDriver).testIMPL(login_ip, userName, userPassword, uiport);
			
			memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
			String memOnEndRemote = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip_remote,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
			Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
			long 		timeStemp = timestamp.getTime() ;
			connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
			connecttoDb(login_ip, Integer.parseInt(memOnStartRemote.substring(0, memOnStartRemote.length() - 1)), Integer.parseInt(memOnEndRemote.substring(0, memOnEndRemote.length() - 1)), timeStemp);

		}
		
		Assert.assertEquals(driverReslut.getResult(), "good");
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
