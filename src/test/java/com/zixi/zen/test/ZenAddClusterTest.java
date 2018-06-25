package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddClusterDriver;

import com.zixi.testing.BaseTestZixiMainComponents;

public class ZenAddClusterTest extends BaseTestZixiMainComponents{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddClusterDriver(); }

	// Test parameters - these parameters will be provided through an appropriate suite's XML file.
	@Parameters({ "zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", 
	"clusterName", "resource_tag_name", "can_input", "can_output", "can_process", "http_streaming_port", "auth_mode",
	"allow_unmanaged_inputs", "allow_unmanaged_outputs", "dns_prefix", "is_auto_scaling", "activation_key",
	"bx_version", "aws_account_name", "region", "min_size", "max_size", "api_user", "api_password", "testid"})
	@Test
	public void addFeederToZen(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, 
	String clusterName, String resource_tag_name, String can_input, String can_output, String can_process, String http_streaming_port, String auth_mode,
	String allow_unmanaged_inputs, String allow_unmanaged_outputs, String dns_prefix, String is_auto_scaling, String activation_key,
	String bx_version, String aws_account_name, String region, String min_size, String max_size, String api_user, String api_password, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", 
		"clusterName", "resource_tag_name", "can_input", "can_output", "can_process", "http_streaming_port", "auth_mode",
		"allow_unmanaged_inputs", "allow_unmanaged_outputs", "dns_prefix", "is_auto_scaling", "activation_key",
		"bx_version", "aws_account_name", "region", "min_size", "max_size", "api_user", "api_password", "testid"}, 
		new String[] {zenUserName, zenUserPass, zenLogin_ip, zenUiport, 
		clusterName, resource_tag_name, can_input, can_output, can_process, http_streaming_port, auth_mode,
		allow_unmanaged_inputs, allow_unmanaged_outputs, dns_prefix, is_auto_scaling, activation_key,
		bx_version, aws_account_name, region, min_size, max_size, api_user, api_password, testid});
		
		driverReslut = ((ZenAddClusterDriver) testDriver).addCluster(zenUserName, zenUserPass, zenLogin_ip, zenUiport, 
		clusterName, resource_tag_name, can_input, can_output, can_process, http_streaming_port, auth_mode,
		allow_unmanaged_inputs, allow_unmanaged_outputs, dns_prefix, is_auto_scaling, activation_key,
		bx_version, aws_account_name, region, min_size, max_size, api_user, api_password);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}
