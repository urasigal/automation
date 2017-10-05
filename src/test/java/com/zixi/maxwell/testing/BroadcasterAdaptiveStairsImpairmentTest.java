package com.zixi.maxwell.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.maxwell.drivers.BroadcasterAdaptiveStairsImpairmentDriver;
import com.zixi.testing.BaseTestZixiMainComponents;


public class BroadcasterAdaptiveStairsImpairmentTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() {  testDriver = new BroadcasterAdaptiveStairsImpairmentDriver(); }

	// Test parameters.
	@Parameters({"maxwell_address", "standart_impairment_server_api_port", "flow_match_control_setmatch", "impairment_control_setimpair", 
	"userName", "userPassword", "login_ip", "uiport", "id", "testid"})
	@Test
	public void configureMaxwellSimulator(String maxwell_address, String standart_impairment_server_api_port, String flow_match_control_setmatch,
	String impairment_control_setimpair, String userName, String userPassword, String login_ip, String uiport, String id, String testid) throws Exception {
		
		//Print this class's name to the log file.
		getLoggerInstance().info(getClass().getName());
		
		buildTestParametersString(new String[] { "maxwell_address", "standart_impairment_server_api_port", "flow_match_control_setmatch", 
		"impairment_control_setimpair", "testid" }, 
		new String[] { maxwell_address, standart_impairment_server_api_port, flow_match_control_setmatch, impairment_control_setimpair, testid });
		
		driverReslut = ((BroadcasterAdaptiveStairsImpairmentDriver) testDriver).testIMPL( maxwell_address,  standart_impairment_server_api_port, 
		flow_match_control_setmatch, impairment_control_setimpair,  userName,  userPassword,  login_ip,  uiport, id); 
		
		Assert.assertEquals(driverReslut.getResult(), "GOOD");
		// Checking if broadcaster has crashes while execution of the test.
	}
	
    // Test parameters.
	@Parameters({ "maxwell_address", "standart_impairment_server_api_port", "flow_match_control_setmatch", 
	"impairment_control_setimpair", "testid"})
	@Test
	public void broadcasterSetGlobalInpairment(String maxwell_address, String standart_impairment_server_api_port,
	String flow_match_control_setmatch, String impairment_control_setimpair, String testid) throws Exception {
		
		//Print this class's name to the log file.
		getLoggerInstance().info(getClass().getName());
		
		buildTestParametersString(new String[] { "maxwell_address", "standart_impairment_server_api_port", "flow_match_control_setmatch", 
		"impairment_control_setimpair", "testid"}, 
		new String[] { maxwell_address, standart_impairment_server_api_port, flow_match_control_setmatch, 
		impairment_control_setimpair, testid});
		
		driverReslut = ((BroadcasterAdaptiveStairsImpairmentDriver) testDriver).testIMPL( maxwell_address,  standart_impairment_server_api_port, 
		flow_match_control_setmatch, impairment_control_setimpair); 
		
		Assert.assertEquals(driverReslut.getResult(), "GOOD");
		// Checking if broadcaster has crashes while execution of the test.
	}
	
	// Test parameters.
	@Parameters({"maxwell_address", "standart_impairment_server_api_port", "flow_match_control_setmatch", 
	"impairment_control_setimpair1", "impairment_control_setimpair2", "g_1050TestCaseNumber", "setlogging", "testid"})
	@Test
	public void g_1050SetTestCaseNumber(String maxwell_address, String standart_impairment_server_api_port, String flow_match_control_setmatch,
	String impairment_control_setimpair1, String impairment_control_setimpair2, String g_1050TestCaseNumber, String setlogging, String testid) throws Exception {
		
		//Print this class's name to the log file.
		getLoggerInstance().info(getClass().getName());
		
		buildTestParametersString(new String[] {"maxwell_address", "standart_impairment_server_api_port",
		"flow_match_control_setmatch", "impairment_control_setimpair1", "impairment_control_setimpair2", "g_1050TestCaseNumber", "setlogging", "testid"}, 
		new String[] {maxwell_address, standart_impairment_server_api_port, flow_match_control_setmatch, impairment_control_setimpair1, 
		impairment_control_setimpair2, g_1050TestCaseNumber, setlogging, testid});
		
		driverReslut = ((BroadcasterAdaptiveStairsImpairmentDriver) testDriver).testIMPL(maxwell_address, standart_impairment_server_api_port, flow_match_control_setmatch, impairment_control_setimpair1, 
		impairment_control_setimpair2, g_1050TestCaseNumber, setlogging); 
		
		Assert.assertEquals(driverReslut.getResult(), "GOOD");
		// Checking if broadcaster has crashes while execution of the test.
	}
}
