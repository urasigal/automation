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
		public void broadcasterUpdateSettingsSetDiffPorts(String userName,String userPass,String login_ip,String uiport,String server_id,String gui_web_port,String uname,
		String aname,String ft_max_quota,String max_cpu,String max_mem,String max_in_bandwidth,String max_out_bandwidth,String admin_https,String use_operator,
		String use_user,String use_observer,String private_port,String public_port, String range, String testid) throws Exception 
		{
			//Print this class name to the log file.
			getLoggerInstance().info(getClass().getName());
			
			// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
			productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
			
			buildTestParametersString(new String[] { "userName","userPass","login_ip","uiport","server_id","gui_web_port","uname","aname","ft_max_quota","max_cpu","max_mem","max_in_bandwidth","max_out_bandwidth",
			"admin_https","use_operator","use_user","use_observer","private_port","public_port", "range", "testid" }, 
			new String[] {userName, userPass, login_ip, uiport, server_id, gui_web_port, uname, aname, ft_max_quota, max_cpu, max_mem, max_in_bandwidth, max_out_bandwidth, admin_https, use_operator,
			use_user,use_observer,private_port,public_port, range, testid});
			StringBuilder privatePorts = new StringBuilder();
			int portRange = Integer.parseInt(range);
			privatePorts.append(private_port).append(",,,0,0");
			for (int i = 1; i < portRange; i++) {
				int privateTmpPort = Integer.parseInt(private_port);
				privateTmpPort ++;
				privatePorts.append("&private_port=" + private_port).append(",,,0,0");
			}
			
			driverReslut = ((BroadcasterUpdateSettingsDriver) testDriver).testIMPL(userName, userPass, login_ip, uiport, server_id, gui_web_port, uname, aname,
			ft_max_quota, max_cpu, max_mem, max_in_bandwidth, max_out_bandwidth, admin_https, use_operator, use_user, use_observer, privatePorts.toString(), public_port); 
			
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
		
		// Enable HTTP automatic push/pull.
		@Parameters({"login_ip", "userName", "userPass", "uiport", "flv_on", "hls_on", "mpd_on", "pls_on", "http_out_ip", "http_out_port",
		"hls_chunk_time", "hls_chunks", "http_auth_cahce_timeout", "http_on", "https_on", "https_out_port", 
		"hls_dvr_duration_s", "hls_no_mem_chunks", "hls_no_dvr", "hls_vod_abs_path_on", "http_ts_auto_in",
		"http_ts_auto_out", "http_ts_buffer_size", "http_ts_smoothing_latency", "tcp_congestion_algo", "testid"})
		@Test
		public void broadcasterEnableAutomaticPushPullSettings(String login_ip, String userName, String userPass, String uiport, String flv_on, 
		String hls_on, String mpd_on, String pls_on, String http_out_ip, String http_out_port,
		String hls_chunk_time, String hls_chunks, String http_auth_cahce_timeout, String http_on, String https_on, String https_out_port, 
		String hls_dvr_duration_s, String hls_no_mem_chunks, String hls_no_dvr, String hls_vod_abs_path_on, String http_ts_auto_in,
		String http_ts_auto_out, String http_ts_buffer_size, String http_ts_smoothing_latency, String tcp_congestion_algo, String testid) 
		throws Exception 
		{	
			// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
			productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
			
			buildTestParametersString(new String[] { "login_ip", "userName", "userPass", "uiport", "flv_on", "hls_on", "mpd_on", "pls_on", "http_out_ip", "http_out_port",
			"hls_chunk_time", "hls_chunks", "http_auth_cahce_timeout", "http_on", "https_on", "https_out_port", 
			"hls_dvr_duration_s", "hls_no_mem_chunks", "hls_no_dvr", "hls_vod_abs_path_on", "http_ts_auto_in",
			"http_ts_auto_out", "http_ts_buffer_size", "http_ts_smoothing_latency", "tcp_congestion_algo", "testid" }, 
			new String[] {login_ip, userName, userPass, uiport, flv_on, hls_on, mpd_on, pls_on, http_out_ip, http_out_port,
			hls_chunk_time, hls_chunks, http_auth_cahce_timeout, http_on, https_on, https_out_port, 
			hls_dvr_duration_s, hls_no_mem_chunks, hls_no_dvr, hls_vod_abs_path_on, http_ts_auto_in,
			http_ts_auto_out, http_ts_buffer_size, http_ts_smoothing_latency, tcp_congestion_algo, testid});
			
			driverReslut = ((BroadcasterUpdateSettingsDriver) testDriver).enableHTTP(login_ip, userName, userPass, uiport, flv_on, hls_on, mpd_on, pls_on, http_out_ip, http_out_port,
			hls_chunk_time, hls_chunks, http_auth_cahce_timeout, http_on, https_on, https_out_port, hls_dvr_duration_s, hls_no_mem_chunks, hls_no_dvr, hls_vod_abs_path_on, http_ts_auto_in,
			http_ts_auto_out, http_ts_buffer_size, http_ts_smoothing_latency, tcp_congestion_algo); 
			
			Assert.assertEquals(driverReslut.getResult(), "GOOD");
			// Checking if broadcaster has crashes while execution of the test.
		}
}
 