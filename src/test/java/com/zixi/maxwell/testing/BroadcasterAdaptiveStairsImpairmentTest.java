package com.zixi.maxwell.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.maxwell.drivers.BroadcasterAdaptiveStairsImpairmentDriver;
import com.zixi.testing.BaseTest;


public class BroadcasterAdaptiveStairsImpairmentTest extends BaseTest {

	@BeforeClass
	public void testInit() {  testDriver = new BroadcasterAdaptiveStairsImpairmentDriver(); }

	// Test parameters.
	@Parameters({"maxwell_address", "standart_impairment_server_api_port", "flow_match_control_setmatch", "impairment_control_setimpair", 
		         "userName", "userPassword", "login_ip", "uiport", "id", "testid"})
	@Test
	public void broadcasterSingleStreamRemoving(String maxwell_address, String standart_impairment_server_api_port, String flow_match_control_setmatch,
	String impairment_control_setimpair, String userName, String userPassword, String login_ip, String uiport, String id, String testid) throws Exception {
		
		//Print this class name to the log file.
		getLoggerInstance().info(getClass().getName());
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		//this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPassword);
		
		buildTestParametersString(new String[] { "maxwell_address", "standart_impairment_server_api_port", "flow_match_control_setmatch", 
		"impairment_control_setimpair", "testid" }, 
		new String[] { maxwell_address, standart_impairment_server_api_port, flow_match_control_setmatch, impairment_control_setimpair, testid });
		
		driverReslut = ((BroadcasterAdaptiveStairsImpairmentDriver) testDriver).testIMPL( maxwell_address,  standart_impairment_server_api_port, 
		flow_match_control_setmatch, impairment_control_setimpair,  userName,  userPassword,  login_ip,  uiport,  id); 
		
		Assert.assertEquals(driverReslut.getResult(), "GOOD");
		// Checking if broadcaster has crashes while execution of the test.
	}
}
