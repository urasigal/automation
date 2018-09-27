package com.zixi.zen.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.ZenAddTargetDriver;
import com.zixi.testing.BaseTestZixiMainComponentsZen;

public class ZenAddUdpTargetTest extends BaseTestZixiMainComponentsZen{
	
	@BeforeClass
	public void testInit() { testDriver = new ZenAddTargetDriver(); }

	@Parameters({"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "targetName", "resource_tag_ids", "targetType", "channel_name",
	"host", "port", "bind_cidr", "rtp", "smpte_2022_fec", "smpte_2022_fec_cols", "smpte_2022_fec_rows", "smoothing", "remux_kbps", "testid"})
	@Test
	public void addUdpTarget(String zenUser, String zenPass, String zenLogin_ip, String zenUiPort, String targetName, String resource_tag_ids, String targetType, String channel_name,
		String host, String port, String bind_cidr, String rtp, String smpte_2022_fec, String smpte_2022_fec_cols, String smpte_2022_fec_rows, String smoothing, String remux_kbps, String testid) throws Exception {
		// Provide parameters to a TestLink.
		buildTestParametersString(new String[] {"zenUser", "zenPass", "zenLogin_ip", "zenUiPort", "targetName", "resource_tag_ids", "targetType", "channel_name",
		"host", "port", "bind_cidr", "rtp", "smpte_2022_fec", "smpte_2022_fec_cols", "smpte_2022_fec_rows", "smoothing", "remux_kbps","testid"}, 
		new String[] {zenUser, zenPass, zenLogin_ip, zenUiPort, targetName, resource_tag_ids, targetType, channel_name,
		host, port, bind_cidr, rtp, smpte_2022_fec, smpte_2022_fec_cols, smpte_2022_fec_rows, smoothing, remux_kbps, testid});
		
		driverReslut = ((ZenAddTargetDriver) testDriver).addUdpRtpTarget( zenUser, zenPass, zenLogin_ip, zenUiPort, targetName, resource_tag_ids, targetType, channel_name,
		host, port, bind_cidr, rtp, smpte_2022_fec, smpte_2022_fec_cols, smpte_2022_fec_rows, smoothing, remux_kbps);
		Assert.assertEquals(driverReslut.getResult(), "true"); 
	}
	
}