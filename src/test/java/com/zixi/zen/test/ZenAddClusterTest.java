package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenAddClusterDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddClusterTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddClusterDriver(); }
	// Test parameters - these parameters will be provided by appropriate suite's XML file.
	@Parameters({ "zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", 
	"clusterName", "resource_tag_name", "can_input", "can_output", "can_process", "http_streaming_port", "auth_mode",
	"allow_unmanaged_inputs", "allow_unmanaged_outputs", "dns_prefix", "is_auto_scaling", "activation_key",
	"bx_version", "aws_account_name", "region", "key_pair", "security_group", "eips", "min_size", "max_size",
	"api_user", "api_password", "vpc_name", "vpc_subnet_name", "testid"})
	@Test
	public void addClusterToZen(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, 
	String clusterName, String resource_tag_name, String can_input, String can_output, String can_process, String http_streaming_port, String auth_mode,
	String allow_unmanaged_inputs, String allow_unmanaged_outputs, String dns_prefix, String is_auto_scaling, String activation_key,
	String bx_version, String aws_account_name, String region, String key_pair, String security_group,
	String eips, String min_size, String max_size, String api_user, String api_password,
	String vpc_name, String vpc_subnet_name, String testid) throws Exception { 
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", 
		"clusterName", "resource_tag_name", "can_input", "can_output", "can_process", "http_streaming_port", "auth_mode",
		"allow_unmanaged_inputs", "allow_unmanaged_outputs", "dns_prefix", "is_auto_scaling", "activation_key",
		"bx_version", "aws_account_name", "region", "key_pair", "security_group", "eips", "min_size", "max_size", "api_user", "api_password", "vpc_name", "vpc_subnet_name", "testid"}, 
		new String[] {zenUserName, zenUserPass, zenLogin_ip, zenUiport, 
		clusterName, resource_tag_name, can_input, can_output, can_process, http_streaming_port, auth_mode,
		allow_unmanaged_inputs, allow_unmanaged_outputs, dns_prefix, is_auto_scaling, activation_key,
		bx_version, aws_account_name, region, key_pair, security_group, eips, min_size, max_size, api_user, api_password, vpc_name, vpc_subnet_name, testid});
		
		driverReslut = ((ZenAddClusterDriver) testDriver).addCluster(zenUserName, zenUserPass, zenLogin_ip, zenUiport, 
		clusterName, resource_tag_name, can_input, can_output, can_process, http_streaming_port, auth_mode,
		allow_unmanaged_inputs, allow_unmanaged_outputs, dns_prefix, is_auto_scaling, activation_key,
		bx_version, aws_account_name, region, key_pair, security_group, eips, min_size, max_size, api_user, api_password, vpc_name, vpc_subnet_name);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
}
