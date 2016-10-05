package com.zixi.testing;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.*;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BxFileTransferAndVodSettingsDriver;

public class BxFileTransferAndVodSettingsTest extends BaseTest{
	
	@BeforeClass
	public void testInit() {
		testDriver = new BxFileTransferAndVodSettingsDriver();
	}

	@Parameters({ "userName","userPass" ,"login_ip", "uiport", "ft_download" , "ft_upload","ft_auto_index", "ft_prog", "ft_encrypt", "ft_bitrate_cache", "ft_aggr",
	"ft_mtu", "ft_init_speed", "ft_cache", "ft_proxy_http_port", "ft_proxy_https_port", "max_download_bitrate", "max_upload_bitrate", "testid"})
	@Test
	public void broadcasterSetBxFileTransferVodSettings(String userName, String userPass, String login_ip, String uiport, String ft_download, String ft_upload,
	String ft_auto_index, String ft_prog, String ft_encrypt, String ft_bitrate_cache, String ft_aggr, String ft_mtu, String ft_init_speed,
	String ft_cache, String ft_proxy_http_port, String ft_proxy_https_port, String max_download_bitrate, String max_upload_bitrate, String testid) throws Exception 
	{
		this.testid = testid;
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		this.version = productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);
		
		testParameters = buildTestParametersString(new String[] { "userName","userPass","login_ip", "uiport", "ft_download", "ft_upload",
		"ft_auto_index", "ft_prog", "ft_encrypt", "ft_bitrate_cache", "ft_aggr", "ft_mtu", "ft_init_speed", "ft_cache", "ft_proxy_http_port",
		"ft_proxy_https_port", "max_download_bitrate", "max_upload_bitrate", "testid" }, 
				
		new String[] { userName, userPass, login_ip, uiport, ft_download , ft_upload, ft_auto_index , ft_prog , ft_encrypt , ft_bitrate_cache ,
		ft_aggr, ft_mtu, ft_init_speed, ft_cache, ft_proxy_http_port, ft_proxy_https_port, max_download_bitrate, max_upload_bitrate, testid});
		
		
		Assert.assertEquals(((BxFileTransferAndVodSettingsDriver) testDriver).testIMPL( userName, userPass ,login_ip, uiport, ft_download, ft_upload, ft_auto_index,
		ft_prog, ft_encrypt, ft_bitrate_cache, ft_aggr, ft_mtu, ft_init_speed, ft_cache, ft_proxy_http_port, ft_proxy_https_port, max_download_bitrate,
		max_upload_bitrate), "1&1");
		
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
