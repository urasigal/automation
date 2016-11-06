package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterConfigMulticastPoolDriver;

public class BroadcasterConfigMulticastPoolTest extends BaseTest{
	
	@BeforeClass
	public void testInit() { 
		// Super class element
		testDriver = new BroadcasterConfigMulticastPoolDriver();
	}

	// Test parameters.
	@Parameters({"login_ip", "userName", "userPassword", "uiport", "multicast_pool_enabled", "multicast_pool_address", "multicast_pool_mask", "multicast_pool_fec_overhead",
	"multicast_pool_port", "multicast_pool_ttl", "multicast_pool_nic", "multicast_pool_tos", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String login_ip, String userName, String userPassword, String uiport, String multicast_pool_enabled,
	String multicast_pool_address, String multicast_pool_mask, String multicast_pool_fec_overhead, String multicast_pool_port, String multicast_pool_ttl, 
	String multicast_pool_nic, String multicast_pool_tos, String  testid) throws Exception 
	{
		//Print this class name to the log file.
		getLoggerInstance().info(getClass().getName());
		
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22", "pidof zixi_broadcaster");
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		testParameters = buildTestParametersString(new String[] { "login_ip", "userName", "userPassword", "uiport", "multicast_pool_enabled", "multicast_pool_address",
		"multicast_pool_mask", "multicast_pool_fec_overhead", "multicast_pool_port", "multicast_pool_ttl", "multicast_pool_nic", "multicast_pool_tos", "testid" }, 
		
		new String[] {login_ip, userName, userPassword, uiport, multicast_pool_enabled, multicast_pool_address, multicast_pool_mask, multicast_pool_fec_overhead,
		multicast_pool_port, multicast_pool_ttl, multicast_pool_nic, multicast_pool_tos, testid});
		
		Assert.assertEquals(((BroadcasterConfigMulticastPoolDriver) testDriver).testIMPL(login_ip, userName, userPassword, uiport, multicast_pool_enabled,
		multicast_pool_address, multicast_pool_mask, multicast_pool_fec_overhead, multicast_pool_port, multicast_pool_ttl, multicast_pool_nic, multicast_pool_tos), "GOOD");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertNotEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}

}
