package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterInputStreamManipulatorDriver;

public class BroadcasterInputStreamManipulatorTest extends BaseTest{
	
	@BeforeClass
	public void testInit() 
	{	
		testDriver = new BroadcasterInputStreamManipulatorDriver();
	}

	@Parameters({ "userName","userPass","login_ip", "uiport", "streamId", "onOff", "streamType", "testid"})
	@Test
	public void broadcasterStopStartInputStream(String userName, String userPass, String login_ip, String uiport,
	String streamId, String onOff, String streamType, String testid) throws Exception {
		
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster");
		
		testParameters = buildTestParametersString(new String[] { "userName","userPass","login_ip", "uiport", "streamId", "stopStart", "streamType", "testid"}, 
		
		new String[] { userName, userPass, login_ip, uiport, streamId, onOff, streamType, testid });
		
		Assert.assertEquals(((BroadcasterInputStreamManipulatorDriver) testDriver).testIMPL( userName, userPass, login_ip, uiport,
		streamId, onOff, streamType),"Applied new configuration to " + streamId);
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster"));
	}
}
