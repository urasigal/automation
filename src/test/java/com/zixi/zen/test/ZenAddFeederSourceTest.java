package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.AddFeederSourceDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class ZenAddFeederSourceTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new AddFeederSourceDriver(); }

	@Parameters({"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "sourceName", "broadcaster_cluster_name", "feeder_name", "broadcaster_id", "input_id",
	"max_bitrate", "latency", "monitor_pids_change", "resource_tag_ids", "password", "encryption",
	"encryption_key", "allow_outputs", "outputs_password", "monitor_only", "cleanup_outputs", "testid"})
	@Test
	public void addFeederSourceToZen(String zenUser, String zenPass, String zenLogin_ip, String zenUiPort, String sourceName, String broadcaster_cluster_name, String feeder_name, String broadcaster_id, String input_id,
	String max_bitrate, String latency, String monitor_pids_change, String resource_tag_ids, String password, String encryption,
	String encryption_key, String allow_outputs, String outputs_password, String monitor_only, String cleanup_outputs, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "sourceName", "broadcaster_cluster_name", "feeder_name", "broadcaster_id", "input_id",
		"max_bitrate", "latency", "monitor_pids_change", "resource_tag_ids", "password", "encryption",
		"encryption_key", "allow_outputs", "outputs_password", "monitor_only", "cleanup_outputs", "testid"}, 
		new String[] {zenUser, zenPass, zenLogin_ip, zenUiPort, sourceName, broadcaster_cluster_name, feeder_name, broadcaster_id, input_id,
		max_bitrate, latency, monitor_pids_change, resource_tag_ids, password, encryption,
		encryption_key, allow_outputs, outputs_password, monitor_only, cleanup_outputs, testid});
		
		driverReslut = ((AddFeederSourceDriver) testDriver).addFeederSource(zenUser, zenPass, zenLogin_ip, zenUiPort, sourceName, broadcaster_cluster_name, feeder_name, broadcaster_id, input_id,
		max_bitrate, latency, monitor_pids_change, resource_tag_ids, password, encryption,
		encryption_key, allow_outputs, outputs_password, monitor_only, cleanup_outputs, testid);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}