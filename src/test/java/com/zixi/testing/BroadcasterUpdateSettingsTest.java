package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcasterConfigMulticastPoolDriver;
import com.zixi.drivers.drivers.BroadcasterUpdateSettingsDriver;

public class BroadcasterUpdateSettingsTest extends BasetTestZixiComponentRestartCase{
	
	@BeforeClass
	public void testInit() {  testDriver = new BroadcasterUpdateSettingsDriver(); }

	// Test parameters.
	@Parameters({"userName","userPass","login_ip","uiport","server_id","gui_web_port","uname","aname","ft_max_quota","max_cpu","max_mem","max_in_bandwidth","max_out_bandwidth",
	"admin_https","use_operator","use_user","use_observer","private_port","public_port", "testid"})
	@Test
	public void broadcasterUpdateSettings(String userName,String userPass,String login_ip,String uiport,String server_id,String gui_web_port,String uname,
	String aname,String ft_max_quota,String max_cpu,String max_mem,String max_in_bandwidth,String max_out_bandwidth,String admin_https,String use_operator,
	String use_user,String use_observer,String private_port,String public_port, String  testid) throws Exception 
	{
		//Print this class name to the log file.
		getLoggerInstance().info(getClass().getName());
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		buildTestParametersString(new String[] { "userName","userPass","login_ip","uiport","server_id","gui_web_port","uname","aname","ft_max_quota","max_cpu","max_mem","max_in_bandwidth","max_out_bandwidth",
		"admin_https","use_operator","use_user","use_observer","private_port","public_port", "testid" }, 
		new String[] {userName,userPass,login_ip,uiport,server_id,gui_web_port,uname,aname,ft_max_quota,max_cpu,max_mem,max_in_bandwidth,max_out_bandwidth,admin_https,use_operator,
		use_user,use_observer,private_port,public_port, testid});
		
		driverReslut = ((BroadcasterUpdateSettingsDriver) testDriver).testIMPL(userName,userPass,login_ip,uiport,server_id,gui_web_port,uname,aname,
		ft_max_quota,max_cpu,max_mem,max_in_bandwidth,max_out_bandwidth,admin_https,use_operator, use_user,use_observer,private_port,public_port); 
		
		Assert.assertEquals(driverReslut.getResult(), "GOOD");
		// Checking if broadcaster has crashes while execution of the test.
	}
	
	
	// Test parameters.
		@Parameters({"userName","userPass","login_ip","uiport","server_id","gui_web_port","uname","aname","ft_max_quota","max_cpu","max_mem","max_in_bandwidth","max_out_bandwidth",
		"admin_https","use_operator","use_user","use_observer","private_port","public_port", "testid"})
		@Test
		public void broadcasterBindPortToIp(String userName,String userPass,String login_ip,String uiport,String server_id,String gui_web_port,String uname,
		String aname,String ft_max_quota,String max_cpu,String max_mem,String max_in_bandwidth,String max_out_bandwidth,String admin_https,String use_operator,
		String use_user,String use_observer,String private_port,String public_port, String  testid) throws Exception 
		{
			//Print this class name to the log file.
			getLoggerInstance().info(getClass().getName());
			
			// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
			productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
			
			buildTestParametersString(new String[] { "userName","userPass","login_ip","uiport","server_id","gui_web_port","uname","aname","ft_max_quota","max_cpu","max_mem","max_in_bandwidth","max_out_bandwidth",
			"admin_https","use_operator","use_user","use_observer","private_port","public_port", "testid" }, 
			new String[] {userName,userPass,login_ip,uiport,server_id,gui_web_port,uname,aname,ft_max_quota,max_cpu,max_mem,max_in_bandwidth,max_out_bandwidth,admin_https,use_operator,
			use_user,use_observer,private_port,public_port, testid});
			
			driverReslut = ((BroadcasterUpdateSettingsDriver) testDriver).testIMPL(userName,userPass,login_ip,uiport,server_id,gui_web_port,uname,aname,
			ft_max_quota,max_cpu,max_mem,max_in_bandwidth,max_out_bandwidth,admin_https,use_operator, use_user,use_observer,private_port,public_port); 
			
			Assert.assertEquals(driverReslut.getResult(), "GOOD");
			// Checking if broadcaster has crashes while execution of the test.
		}
	
	
}
 