package com.zixi.transcoder.testing;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterTrannscodeStreamDriver;
import com.zixi.drivers.drivers.BroadcasterTransoderGetStatsDriver;
import com.zixi.testing.BaseTestZixiMainComponents;

public class NaughtyStringsTranscodedInputNameTest extends BaseTestZixiMainComponents {

	@BeforeClass
	public void testInit() { testDriver = new BroadcasterTrannscodeStreamDriver();}

	@Parameters({"userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old",
	"fast_connect", "kompression", "enc_type", "enc_key", "rec_history", "rec_duration", "src", "ap", "use_hw", "ll", "all_pids",
	"bit", "profile_name", "mode", "vp", "smoothing", "max_va_diff_ms", "testid"})
	
	@Test // This test is actually transcodes an input test.
	public void broadcasterSingleInputStreamstatisticAnilyzer(String userName, String userPass, String login_ip, String uiport, String type,String id,
	String matrix, String max_outputs, String mcast_out, String time_shift, String old,
	String fast_connect, String kompression, String enc_type, String enc_key,
	String rec_history, String rec_duration, String src, String ap, String use_hw, String ll, String all_pids,
	String bit, String profile_name, String mode,  @Optional("1") String vp, @Optional("1500") String smoothing, @Optional("1500") String max_va_diff_ms, String testid)
	throws Exception {

		productAboutDriver.getBroadcasterVersion(login_ip,uiport, userName, userPass);

		buildTestParametersString(new String[] { "userName", "userPass", "login_ip", "uiport", "type", "id", "matrix", "max_outputs", "mcast_out", "time_shift", "old",
		"fast_connect", "kompression", "enc_type", "enc_key", "rec_history", "rec_duration", "src", "ap", "use_hw", "ll", "all_pids", "bit", "profile_name", "mode", "vp", "smoothing", "max_va_diff_ms", "testid" },
		new String[] { userName,  userPass,  login_ip,  uiport,  type, id,  matrix,  max_outputs,  mcast_out,  time_shift,  old,
		fast_connect,  kompression,  enc_type,  enc_key, rec_history,  rec_duration,  src,  ap, use_hw, ll,  all_pids,  bit,  profile_name, mode, vp, smoothing, max_va_diff_ms, testid });

		String rowJsonFromFile = null;
		try(BufferedReader in = new BufferedReader(new FileReader("src/main/resources/input_names")))
		{
			while((rowJsonFromFile = in.readLine())!=null){}
			JSONArray naughtyInputNames = new JSONArray(rowJsonFromFile);
			for(int i = 0; i < naughtyInputNames.length(); i++)
			{
				driverReslut = ((BroadcasterTrannscodeStreamDriver) testDriver) .testIMPL( userName,  userPass,  login_ip,  uiport,  type, naughtyInputNames.getString(i),
			    matrix,  max_outputs,  mcast_out,  time_shift,  old, fast_connect,  kompression,  enc_type,  enc_key,  rec_history,  rec_duration,  src, ap, use_hw, ll,  all_pids,
			    bit,  profile_name, mode, vp, smoothing, max_va_diff_ms);
				Assert.assertEquals(driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
				
				Thread.sleep(60_000);
				
				driverReslut = ((BroadcasterTransoderGetStatsDriver) testDriver).getTranscoderStatistics(userName, userPass, login_ip, uiport, 
				naughtyInputNames.getString(i), testid);
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
					Assert.assertEquals("muxer_resets " + resultJson.getInt(0) + " decoder_resets " +  resultJson.getInt(1) +
					" encoder_resets " + resultJson.getInt(2) + " muxer_drops " + resultJson.getInt(3) +
					" muxer_resets " + resultJson.getInt(4) + " raw_frame_drops " + resultJson.getInt(5) + " restarts " + resultJson.getInt(6) + 
					" src_cmp_frame_demux_drop " + resultJson.getInt(8) + " src_cmp_frame_overflows " + resultJson.getInt(9), 
				
					"muxer_resets " + 0 +  " decoder_resets " + 0 + " encoder_resets " + 0 + " muxer_drops " + 0 +
					" muxer_resets " + 0 + " raw_frame_drops " + 0 + " restarts " + 0 + " src_cmp_frame_demux_drop " + 0, " src_cmp_frame_overflows " + 0);
					
					// Checking if broadcaster has crashes while execution of the test.
					Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22",  "pidof zixi_broadcaster"));
				}else
				{
					Assert.assertEquals("muxer_resets " + resultJson.getInt(0) + " decoder_resets " +  resultJson.getInt(1) +
					" encoder_resets " + resultJson.getInt(2) + " muxer_drops " + resultJson.getInt(3) +
					" muxer_resets " + resultJson.getInt(4) + " raw_frame_drops " + resultJson.getInt(5) + " restarts " + resultJson.getInt(6) + 
					" smoother_drops " + resultJson.getInt(7) + " src_cmp_frame_demux_drop " + resultJson.getInt(8) + " src_cmp_frame_overflows " + resultJson.getInt(9), 
				 
					"muxer_resets " + 0 + " decoder_resets " +  0 + " encoder_resets " + 0 + " muxer_drops " + 0 +
					" muxer_resets " + 0 + " raw_frame_drops " + 0 + " restarts " + 0 + " src_cmp_frame_demux_drop " + 0 + " src_cmp_frame_overflows " + 0 );
					
					// Checking if broadcaster has crashes while execution of the test.
					Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234", 
					login_ip,  "22",  "pidof zixi_broadcaster"));
				}
		
			}
		}catch(Exception e){System.out.println(e.getMessage()); throw new Exception();} 
		
		driverReslut = ((BroadcasterTrannscodeStreamDriver) testDriver) .testIMPL( userName,  userPass,  login_ip,  uiport,  type, id,
	    matrix,  max_outputs,  mcast_out,  time_shift,  old, fast_connect,  kompression,  enc_type,  enc_key,  rec_history,  rec_duration,  src, ap, use_hw, ll,  all_pids,
	    bit,  profile_name, mode, vp, smoothing, max_va_diff_ms);
		
		Assert.assertEquals(driverReslut.getResult(), "Stream " + "'" + id + "'" + " added.");
	}
}
