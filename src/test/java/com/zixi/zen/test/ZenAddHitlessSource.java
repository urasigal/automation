package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.AddFeederSourceDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddHitlessSource extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new AddFeederSourceDriver(); }

	@Parameters({"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "name", "broadcaster_cluster_id", "feeder_id", "broadcaster_id", "target_broadcaster_id",
		"input_id", "max_bitrate", "hitless_failover_source_ids", "latency", "monitor_pids_change", "resource_tag_ids",
		"password", "encryption", "encryption_key", "allow_outputs", "outputs_password", "monitor_only", "testid"})
	@Test
	public void addFeederSourceToZen(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, String name, 
		String broadcaster_cluster_id, String feeder_id, String broadcaster_id, String target_broadcaster_id,
		String input_id, String max_bitrate, String hitless_failover_source_ids, String latency, String monitor_pids_change, String resource_tag_ids,
		String password, String encryption, String encryption_key, String allow_outputs, String outputs_password, String monitor_only, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "name", "broadcaster_cluster_id", "feeder_id", "broadcaster_id", "target_broadcaster_id",
		"input_id", "max_bitrate", "hitless_failover_source_ids", "latency", "monitor_pids_change", "resource_tag_ids",
		"password", "encryption", "encryption_key", "allow_outputs", "outputs_password", "monitor_only", "testid"}, 
		new String[] {zenUserName, zenUserPass, zenLogin_ip, zenUiport, name, broadcaster_cluster_id, feeder_id, broadcaster_id, target_broadcaster_id,
		input_id, max_bitrate, hitless_failover_source_ids, latency, monitor_pids_change, resource_tag_ids,
		password, encryption, encryption_key, allow_outputs, outputs_password, monitor_only, testid});
		
		driverReslut = ((AddFeederSourceDriver) testDriver).addHitlessSource(zenUserName, zenUserPass, zenLogin_ip, zenUiport, name, broadcaster_cluster_id, feeder_id, broadcaster_id, target_broadcaster_id,
				input_id, max_bitrate, hitless_failover_source_ids, latency, monitor_pids_change, resource_tag_ids,
				password, encryption, encryption_key, allow_outputs, outputs_password, monitor_only);
		
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}