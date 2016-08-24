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
	
	
	// Deletes a feeder bonded output stream while a output stream is consists of a two bonded links.
	@Parameters({"userName", "userPass", "login_ip", "uiport", "id", "mip", "port", "ip", "prog", "chan", "type", "nic1", "nic2", "dest_host1", "dest_host2" ,"testid"})
	@Test
	public void broadcasterBondedTwoLinkStreamRemoving(String userName, String userPass, String login_ip, String uiport, String id, String mip, 
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
	
	// Deletes a feeder bonded output stream while a output stream is consists of a three bonded links.
	@Parameters({"userName", "userPass", "login_ip", "uiport", "id", "mip", "port", "ip", "prog", "chan", "type", "nic1", "nic2", "nic3", 
		"dest_host1", "dest_host2", "dest_host3", "testid"})
	@Test
	public void broadcasterBondedThreeLinkStreamRemoving(String userName, String userPass, String login_ip, String uiport, String id, String mip, 
		String port, String ip, String prog, String chan, String type, String nic1, String nic2, String nic3, String dest_host1,
		String dest_host2, String dest_host3, String testid) throws Exception {
		
		this.testid = testid;
		
		// Writes test results to the TestLink.
		testParameters = buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "id", "mip", "port", "ip", "prog", "chan", "type",
		"nic1", "nic2", "nic3", "dest_host1", "dest_host2", "dest_host3", "testid"}, 
		
		new String[] {userName, userPass, login_ip, uiport, id, mip, port, ip, prog, chan, type, nic1, nic2, nic3, dest_host1, 
		dest_host2, dest_host3, testid});
		
		Assert.assertEquals(((FeederOutputDeletionDriver) testDriver).testIMPL(
		userName, userPass, login_ip, uiport, id, mip, port, ip, prog, chan, type, nic1, nic2, nic3, dest_host1, dest_host2, dest_host3), "Output deleted.");
	}
	
}
