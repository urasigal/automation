package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddAdaptiveChannelDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;


public class ZenAddAdaptiveChannelTest  extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddAdaptiveChannelDriver(); }

	// Add transcoded channel to the ZEN master.
	@Parameters({"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "type", "name", "resource_tag_ids", "broadcaster_cluster_id", "adaptive",
	    "delivery", "is_transcoding", "is_source_included", "inputs", "log_scte", "profile_names", "testid"})
	@Test
	public void addAdaptiveChannel(String zenUser, String zenPass, String zenLogin_ip, String zenUiPort, String type, 
	String name, String resource_tag_ids, String broadcaster_cluster_id, String adaptive,
	String delivery, String is_transcoding, String is_source_included, String inputs, String log_scte, String profile_names, 
	String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "type", "name", "resource_tag_ids",
		"broadcaster_cluster_id", "adaptive", "delivery", "is_transcoding", "is_source_included", "inputs", "log_scte", "profile_names","testid"}, 
		new String[] {zenUser, zenPass, zenLogin_ip, zenUiPort, type, name, resource_tag_ids, broadcaster_cluster_id, adaptive,
		delivery, is_transcoding, is_source_included, inputs, log_scte, profile_names, testid});
		
		driverReslut = ((ZenAddAdaptiveChannelDriver) testDriver).addAdaptiveChannel(zenUser, zenPass, zenLogin_ip, zenUiPort, 
		type, name, resource_tag_ids, broadcaster_cluster_id, adaptive, delivery, is_transcoding, is_source_included, inputs, log_scte, profile_names);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}