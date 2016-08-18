package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.FeederOutputDeletionDriver;
import com.zixi.drivers.TestDriver;

public class FeederBondedOutputDeletioinTest extends BaseTest{

	private TestDriver testDriver;

	@BeforeClass
	public void testInit() {
		testDriver = new FeederOutputDeletionDriver();
	}
	// id:zixi://bonded(feederout)#10.7.0.66:2088@10.7.0.65, 192.168.60.66:2088@192.168.50.65
		
	@Parameters({"userName", "userPass", "login_ip", "uiport", "id", "mip", "port", "ip", "prog", "chan", "type", "nic1", "nic2", "dest_host1", "dest_host2" ,"testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String userName, String userPass, String login_ip, String uiport, String id, String mip, 
		String port, String ip, String prog, String chan, String type, String nic1, String nic2, String dest_host1,
		String dest_host2, String testid) throws Exception {
		
		this.testid = testid;
		
		// Writes test results to the TestLink.
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "id", "mip", "port", "ip", "prog", "chan", "type",
		"nic1", "nic2", "dest_host1", "dest_host2" ,"testid" }, 
		
		new String[] {userName, userPass, login_ip, uiport, id, mip, port, ip, prog, chan, type, nic1, nic2, dest_host1, 
		dest_host2 ,testid});
		
		
		Assert.assertEquals(((FeederOutputDeletionDriver) testDriver).testIMPL(
		userName, userPass, login_ip, uiport, id, mip, port, ip, prog, chan, type, nic1, nic2, dest_host1, dest_host2), "Output deleted.");
	}
	
}
