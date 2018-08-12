package com.zixi.testing;

import java.sql.Timestamp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BxFileTransferAndVodSettingsDriver;

public class BxFileTransferAndVodSettingsTest extends BaseTestZixiMainComponents{
	
	@BeforeClass 
	public void testInit() { testDriver = new BxFileTransferAndVodSettingsDriver(); }

	@Parameters({ "userName","userPass" ,"login_ip", "uiport", "ft_download" , "ft_upload","ft_auto_index", "ft_prog", "ft_encrypt", "ft_bitrate_cache", "ft_aggr",
	"ft_mtu", "ft_init_speed", "ft_cache", "ft_proxy_http_port", "ft_proxy_https_port", "max_download_bitrate", "max_upload_bitrate","ft_zixi_index", "ft_zixi_index", "testid"})
	@Test
	public void broadcasterSetBxFileTransferVodSettings(String userName, String userPass, String login_ip, String uiport, String ft_download, String ft_upload,
	String ft_auto_index, String ft_prog, String ft_encrypt, String ft_bitrate_cache, String ft_aggr, String ft_mtu, String ft_init_speed,
	String ft_cache, String ft_proxy_http_port, String ft_proxy_https_port, String max_download_bitrate, String max_upload_bitrate, String ft_zixi_index, String testid) throws Exception 
	{
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		productAboutDriver.getBroadcasterVersion(login_ip, uiport, userName, userPass);		
		memOnStart = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		buildTestParametersString(new String[] { "userName","userPass","login_ip", "uiport", "ft_download", "ft_upload",
		"ft_auto_index", "ft_prog", "ft_encrypt", "ft_bitrate_cache", "ft_aggr", "ft_mtu", "ft_init_speed", "ft_cache", "ft_proxy_http_port",
		"ft_proxy_https_port", "max_download_bitrate", "max_upload_bitrate", "ft_zixi_index", "testid" }, 
		new String[] { userName, userPass, login_ip, uiport, ft_download , ft_upload, ft_auto_index , ft_prog , ft_encrypt , ft_bitrate_cache ,
		ft_aggr, ft_mtu, ft_init_speed, ft_cache, ft_proxy_http_port, ft_proxy_https_port, max_download_bitrate, max_upload_bitrate, ft_zixi_index, testid});
		
		driverReslut = ((BxFileTransferAndVodSettingsDriver) testDriver).testIMPL( userName, userPass ,login_ip, uiport, ft_download, ft_upload, ft_auto_index,
		ft_prog, ft_encrypt, ft_bitrate_cache, ft_aggr, ft_mtu, ft_init_speed, ft_cache, ft_proxy_http_port, ft_proxy_https_port, max_download_bitrate, max_upload_bitrate, ft_zixi_index);
		
		memOnEnd = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "ps v `pidof zixi_broadcaster` | tail -n 1 |  awk '{print $8}'");
		Timestamp 	timestamp = new Timestamp(System.currentTimeMillis());
		long 		timeStemp = timestamp.getTime() ;
		connecttoDb(login_ip, Integer.parseInt(memOnStart.substring(0, memOnStart.length() - 1)), Integer.parseInt(memOnEnd.substring(0, memOnEnd.length() - 1)), timeStemp);
		
		Assert.assertEquals(driverReslut.getResult(), "1&1");
		// Checking if broadcaster has crashes while execution of the test.
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster"));
	}
}
