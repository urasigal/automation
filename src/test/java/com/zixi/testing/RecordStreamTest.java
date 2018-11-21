package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
		
		buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "id", "on", "cpuFolder", "testid"}, 
		new String[] {userName, userPass, login_ip, uiport, id, on, cpuFolder, testid});
		
		Assert.assertEquals(((BroadcasterFileRecordDriver) testDriver).inputRecord(userName, userPass, login_ip, uiport, id, on, cpuFolder), "good");
	}
}