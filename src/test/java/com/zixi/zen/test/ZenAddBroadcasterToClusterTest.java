package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddBroadcasterDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddBroadcasterToClusterTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddBroadcasterDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "bxName", "streaming_ip", "private_ip", "standby", "api_user",
	"api_password", "remote_access_key_name", "broadcaster_cluster_name", "testid"})
	@Test
	public void addBroadcasterToClusterTest(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, String bxName, String streaming_ip,
	String private_ip, String standby, String api_user, String api_password, String remote_access_key_name, String broadcaster_cluster_name, String testid)
	throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "bxName", "streaming_ip", "private_ip", "standby", "api_user",
		"api_password", "remote_access_key_name", "broadcaster_cluster_name", "testid"}, 
		new String[] {zenUserName, zenUserPass, zenLogin_ip, zenUiport, bxName, streaming_ip, private_ip, standby, api_user,
		api_password, remote_access_key_name, broadcaster_cluster_name, testid});
		
		driverReslut = ((ZenAddBroadcasterDriver) testDriver).addBroadcasterToCluster(zenUserName, zenUserPass, zenLogin_ip, zenUiport, bxName, streaming_ip, private_ip, standby, api_user,
		api_password, remote_access_key_name, broadcaster_cluster_name );
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}
