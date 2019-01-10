package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterFileRecordDriver;

public class RecordStreamTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	
	public void testInit() {
		testDriver = new BroadcasterFileRecordDriver();
	}
	
	/// Used in multiple file recording testing.
	@Parameters({ "userName", "userPass", "login_ip", "uiport", "id", "on", "cpuFolder", "testid" })
	@Test
	public void broadcasterRecordInout(String userName, String userPass, String login_ip,
	String uiport, String id, String on, String cpuFolder, String testid)
	throws Exception {
		
		// Get broadcaster's process id, will be used later after the test in order to check if the broadcaster's pid remains the same. 
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "id", "on", "cpuFolder", "testid"}, 
		new String[] {userName, userPass, login_ip, uiport, id, on, cpuFolder, testid});
		
		Assert.assertEquals(((BroadcasterFileRecordDriver) testDriver).inputRecord(userName, userPass, login_ip, uiport, id, on, cpuFolder), "good");
		// Checking if broadcaster has crashed while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}