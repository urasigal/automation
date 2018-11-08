package com.zixi.transcoder.testing;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterTransoderGetStatsDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class BroadcasterTransoderGetStatsTest extends BaseTestZixiMainComponents {
	@BeforeClass
	public void testInit() { testDriver = new BroadcasterTransoderGetStatsDriver(); }

	@Parameters({ "userName", "userPass", "login_ip", "uiport", "stream_name", "mode", "testid"})
	@Test
	public void broadcasterGetTranscoderStats(String userName, String userPass, String login_ip, String uiport, 
	String stream_name, @Optional("vbr") String mode, String testid) throws Exception
	{
		sutProcessId = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ip,  "22",  "pidof zixi_broadcaster");
		
		buildTestParametersString(new String[] {"userName", "userPass", "login_ip", "uiport", "stream_name", "mode", "testid"}, 
		new String[] {userName, userPass, login_ip, uiport, stream_name, mode, testid});
		
		driverReslut = ((BroadcasterTransoderGetStatsDriver) testDriver).getTranscoderStatistics(userName, userPass, login_ip, uiport, 
		stream_name, testid);
		//1 muxer_resets,
		//2 decoder_resets, 
		//3 encoder_resets,
		//4 muxer_drops,
		//5 muxer_resets,
		//6 raw_frame_drops,
		//7 restarts,
		//8 smoother_drops,
		//9 src_cmp_frame_drops
		JSONArray resultJson = new JSONArray(driverReslut.getResult());
		if(mode.equals("vbr"))
		{
			Assert.assertEquals("muxer_resets " + resultJson.getString(0) + " decoder_resets " +  resultJson.getString(1) +
			" encoder_resets " + resultJson.getString(2) + " muxer_drops " + resultJson.getString(3) +
			" muxer_resets " + resultJson.getString(4) + " raw_frame_drops " + resultJson.getString(5) + " restarts " + resultJson.getString(6) + 
			" src_cmp_frame_drops " + resultJson.getString(8), 
		
			"muxer_resets " + 0 + " decoder_resets " +  0 +
			" encoder_resets " + 0 + " muxer_drops " + 0 +
			" muxer_resets " + 0 + " raw_frame_drops " + 0 + " restarts " + 0 + 
			" src_cmp_frame_drops " + 0);
			
			// Checking if broadcaster has crashes while execution of the test.
			Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", 
			login_ip,  "22",  "pidof zixi_broadcaster"));
		}else
		{
			Assert.assertEquals("muxer_resets " + resultJson.getInt(0) + " decoder_resets " +  resultJson.getInt(1) +
			" encoder_resets " + resultJson.getInt(2) + " muxer_drops " + resultJson.getInt(3) +
			" muxer_resets " + resultJson.getInt(4) + " raw_frame_drops " + resultJson.getInt(5) + " restarts " + resultJson.getInt(6) + 
			" smoother_drops " + resultJson.getInt(7) + " src_cmp_frame_drops " + resultJson.getInt(8), 
		
			"muxer_resets " + 0 + " decoder_resets " +  0 +
			" encoder_resets " + 0 + " muxer_drops " + 0 +
			" muxer_resets " + 0 + " raw_frame_drops " + 0 + " restarts " + 0 + 
			" smoother_drops " + 0 + " src_cmp_frame_drops " + 0 );
			
			// Checking if broadcaster has crashes while execution of the test.
			Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234", 
			login_ip,  "22",  "pidof zixi_broadcaster"));
		}
	}
}
