package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterConfigMulticastPoolDriver;

public class BroadcasterConfigMulticastPoolTest extends BasetTestZixiComponentRestartCase {
	
	@BeforeClass
	public void testInit() {  testDriver = new BroadcasterConfigMulticastPoolDriver(); }

	// Test parameters.
	@Parameters({"login_ip", "userName", "userPassword", "uiport", "multicast_pool_enabled", "multicast_pool_address", "multicast_pool_mask", "multicast_pool_fec_overhead",
	"multicast_pool_port", "multicast_pool_ttl", "multicast_pool_nic", "multicast_pool_tos", "testid"})
	@Test
	public void broadcasterConfigureMulticastSetup(String login_ip, String userName, String userPassword, String uiport, String multicast_pool_enabled,
	String multicast_pool_address, String multicast_pool_mask, String multicast_pool_fec_overhead, String multicast_pool_port, String multicast_pool_ttl, 
	String multicast_pool_nic, String multicast_pool_tos, String  testid) throws Exception 
	{	
		// Retrieve the product version.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		
		buildTestParametersString(new String[] { "login_ip", "userName", "userPassword", "uiport", "multicast_pool_enabled", "multicast_pool_address",
		"multicast_pool_mask", "multicast_pool_fec_overhead", "multicast_pool_port", "multicast_pool_ttl", "multicast_pool_nic", "multicast_pool_tos", "testid" }, 
		new String[] {login_ip, userName, userPassword, uiport, multicast_pool_enabled, multicast_pool_address, multicast_pool_mask, multicast_pool_fec_overhead,
		multicast_pool_port, multicast_pool_ttl, multicast_pool_nic, multicast_pool_tos, testid});
		
		driverReslut = ((BroadcasterConfigMulticastPoolDriver) testDriver).testIMPL(login_ip, userName, userPassword, uiport, multicast_pool_enabled,
		multicast_pool_address, multicast_pool_mask, multicast_pool_fec_overhead, multicast_pool_port, multicast_pool_ttl, multicast_pool_nic, multicast_pool_tos); 
		
		Assert.assertEquals(driverReslut.getResult(), "GOOD");
	}
}
