package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.FFMPEGImageStatisticTestDriver;

public class FFMPEGImageStatisticWithCommandTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() {
		// This is a test driver.
		testDriver = new FFMPEGImageStatisticTestDriver();
	}

	@Parameters( { "login_ip", "set_bash_command",  "release_bash_command", "testid"})
	@Test
	public void broadcasterSingleInputStreamStatisticAnilyzer(String login_ip, String set_bash_command,  
	String release_bash_command, String testid) throws InterruptedException {
		
		buildTestParametersString(new String[] { "testid" }, 
		new String[] {testid });
		BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  set_bash_command);
		
		driverReslut = ((FFMPEGImageStatisticTestDriver) testDriver).testStatistic().getResultObj();
		
		BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  release_bash_command);
		
		Assert.assertEquals(driverReslut.getResult(), "good");
	}
}