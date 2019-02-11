package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddTargetDriver;
import com.zixi.testing.BaseTestZixiMainComponents;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddPullTargetTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddTargetDriver(); }

	@Parameters({"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "targetName", "resource_tag_ids", "targetType", "channel_name",
		"password",	"receiver_id", "broadcaster_id", "output_id", "receiver_name", "zixi_encryption_key", "output_name", "testid"})
	@Test
	public void addFeederSourceToZen(String zenUser, String zenPass, String zenLogin_ip, String zenUiPort,
    String targetName, String resource_tag_ids, String targetType, String channel_name,
    String password, String receiver_id, String broadcaster_id, String output_id, String receiver_name, 
    String zixi_encryption_key, String output_name, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "targetName", "resource_tag_ids", "targetType", "channel_name",
		"password",	"receiver_id", "broadcaster_id", "output_id", "receiver_name", "zixi_encryption_key", "output_name", "testid"}, 
		new String[] {zenUser, zenPass, zenLogin_ip, zenUiPort, targetName, resource_tag_ids, targetType, channel_name,
		password, receiver_id, broadcaster_id, output_id, receiver_name, zixi_encryption_key, output_name, testid});
		
		driverReslut = ((ZenAddTargetDriver) testDriver).addPullBroadcasterTarget(zenUser, zenPass, zenLogin_ip, zenUiPort,
		targetName, resource_tag_ids, targetType, channel_name,
		password, receiver_id, broadcaster_id, output_id, receiver_name, zixi_encryption_key, output_name);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}