package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.ZenAddTargetDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddPushTargetTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddTargetDriver(); }

	@Parameters({"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "name", "resource_tag_ids", "type", "channel_name",
	"is_bonding", "target", "alt_target", "stream_id", "password", "latency", "zixi_encryption_key", "testid"})
	@Test
	public void addPushTarget(String zenUser, String zenPass, String zenLogin_ip, String zenUiPort, String name, String resource_tag_ids, String type, String channel_name,
		String is_bonding, String target, String alt_target, String stream_id, String password, String latency, String zixi_encryption_key, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "name", "resource_tag_ids", "type", "channel_name",
		"is_bonding", "target", "alt_target", "stream_id", "password", "latency", "zixi_encryption_key", "testid"}, 
		new String[] {zenUser, zenPass, zenLogin_ip, zenUiPort, name, resource_tag_ids, type, channel_name,
		is_bonding, target, alt_target, stream_id, password, latency, zixi_encryption_key, testid});
		
		driverReslut = ((ZenAddTargetDriver) testDriver).addPushTarget( zenUser, zenPass, zenLogin_ip, zenUiPort, name, resource_tag_ids, type, channel_name,
		is_bonding, target, alt_target, stream_id, password, latency, zixi_encryption_key);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}