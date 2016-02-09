package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.FeederOutputDeletionDriver;
import com.zixi.drivers.TestDriver;

public class FeederUdpOutputDeletioinTest extends BaseTest{
	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new FeederOutputDeletionDriver();
	}

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "id", "mip",
			"port", "ip", "prog", "chan", "type","host", "udpport" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName,
			String userPass, String login_ip, String uiport, String id,
			String mip, String port, String ip, String prog, String chan,
			String type, String host, String udpport, String testid) throws InterruptedException {
		this.testid = testid;
		
		
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "id", "mip",
				"port", "ip", "prog", "chan", "type","host", "udpport" ,"testid" }, 
				
				new String[] {"userName", "userPass", "login_ip", "uiport", "id", "mip",
				"port", "ip", "prog", "chan", "type","host", "udpport" ,"testid" });
		
		
		testParameters = buildTestParametersString(new String[] { userName, userPass, login_ip, uiport, id, mip,
				port, ip, prog, chan, type,host, udpport ,testid }, 
				
				new String[] { userName, userPass, login_ip, uiport, id, mip,
				port, ip, prog, chan, type,host, udpport ,testid });
		
		
		Assert.assertEquals(((FeederOutputDeletionDriver) testDriver).testUdpIMPL(
				userName, userPass, login_ip, uiport, id, mip, port, ip, prog,
				chan, type, host, udpport), "Output deleted.");
	}
}
