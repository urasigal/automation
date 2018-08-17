package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.ZenDeleteClusterDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenDeleteClusterTest extends BaseTestZixiMainComponentsZen {
	
	@BeforeClass
	public void testInit() { testDriver = new ZenDeleteClusterDriver(); }

	// Test parameters - these parameters will be provided by appropriate suite's XML file.
	@Parameters({ "zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "clusterName", "testid"})
	@Test
	public void deleteClusterFromZen(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, 
	String clusterName, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "clusterName", "testid"}, 
		new String[] {zenUserName, zenUserPass, zenLogin_ip, zenUiport, clusterName, testid});
		
		driverReslut = ((ZenDeleteClusterDriver) testDriver).deleteCluster(zenUserName, zenUserPass, zenLogin_ip, zenUiport, clusterName);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
	// Test parameters - these parameters will be provided by appropriate suite's XML file.
		@Parameters({ "zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "clusterName", "testid"})
		@Test
		public void tryDeleteClusterFromZen(String zenUserName, String zenUserPass, String zenLogin_ip, String zenUiport, 
		String clusterName, String testid) throws Exception {
			// Provide parameters to a TestLink.
			buildTestParametersString(new String[] {"zenUserName", "zenUserPass", "zenLogin_ip", "zenUiport", "clusterName", "testid"}, 
			new String[] {zenUserName, zenUserPass, zenLogin_ip, zenUiport, clusterName, testid});
			
			driverReslut = ((ZenDeleteClusterDriver) testDriver).deleteCluster(zenUserName, zenUserPass, zenLogin_ip, zenUiport, clusterName);
			Assert.assertEquals(driverReslut.getResult(), "{\"success\":false,\"error\":\"Unauthorized\"}"); 
		}
}