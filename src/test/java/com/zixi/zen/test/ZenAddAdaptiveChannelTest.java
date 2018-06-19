package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.ZenAddAdaptiveChannelDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class ZenAddAdaptiveChannelTest  extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddAdaptiveChannelDriver(); }

	@Parameters({"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "type", "name", "resource_tag_ids", "broadcaster_cluster_id", "adaptive",
	    "delivery", "is_transcoding", "is_source_included", "inputs", "testid"})
	@Test
	public void addAdaptiveChannel(String zenUser, String zenPass, String zenLogin_ip, String zenUiPort, String type, 
	String name, String resource_tag_ids, String broadcaster_cluster_id, String adaptive,
	String delivery, String is_transcoding, String is_source_included, String inputs, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "type", "name", "resource_tag_ids",
		"broadcaster_cluster_id", "adaptive", "delivery", "is_transcoding", "is_source_included", "inputs", "testid"}, 
		new String[] {zenUser, zenPass, zenLogin_ip, zenUiPort, type, name, resource_tag_ids, broadcaster_cluster_id, adaptive,
		delivery, is_transcoding, is_source_included, inputs, testid});
		
		driverReslut = ((ZenAddAdaptiveChannelDriver) testDriver).addAdaptiveChannel(zenUser, zenPass, zenLogin_ip, zenUiPort, 
		type, name, resource_tag_ids, broadcaster_cluster_id, adaptive, delivery, is_transcoding, is_source_included, inputs);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}