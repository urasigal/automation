package com.zixi.transcoder.testing;

import org.json.JSONArray;
import org.json.JSONObject;
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
		
		// An example of the output Json:
//	    {
//    	  "disconnetions": 0,
//    	  "trans_stats": [
//    	    0,
//    	    0,
//    	    0,
//    	    0,
//    	    0,
//    	    0,
//    	    0,
//    	    0,
//    	    0,
//    	    0
//    	  ]
//	    }
		
		//1 muxer_resets,
		//2 decoder_resets, 
		//3 encoder_resets,
		//4 muxer_drops,
		//5 muxer_resets,
		//6 raw_frame_drops,
		//7 restarts,
		//8 smoother_drops,
		//9 src_cmp_frame_drops
		JSONObject resultJsonObj = new JSONObject(driverReslut.getResult());
		int streamConnections = resultJsonObj.getInt("connections");
		JSONArray trans_stats  = resultJsonObj.getJSONArray("trans_stats");
		if(mode.equals("vbr")){
			Assert.assertEquals	(	"muxer_resets " 			 + trans_stats.getInt(0) +
									" decoder_resets "  		 +  trans_stats.getInt(1)+
									" encoder_resets "  		 + trans_stats.getInt(2) + 
									" muxer_drops " 			 + trans_stats.getInt(3) +
									" muxer_resets " 			 + trans_stats.getInt(4) + 
									" raw_frame_drops " 		 + trans_stats.getInt(5) +
									" restarts " 				 + trans_stats.getInt(6) + 
									" src_cmp_frame_demux_drop " + trans_stats.getInt(8) + 
									" src_cmp_frame_overflows "  + trans_stats.getInt(9) +
									" connections "			     + streamConnections,
									
									"muxer_resets " 	         + 0 + 
									" decoder_resets " 	         + 0 + 
									" encoder_resets " 		   	 + 0 + 
									" muxer_drops " 			 + 0 +
									" muxer_resets " 	         + 0 + 
									" raw_frame_drops " 		 + 0 + 
									" restarts " 				 + 0 + 
									" src_cmp_frame_demux_drop " + 0 +
									" src_cmp_frame_overflows "  + 0 +
									" connections "              + 1);
			// Checking if broadcaster has crashes while execution of the test.
		    Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root", "zixiroot1234", login_ip, "22",  "pidof zixi_broadcaster"));
		}else{
			Assert.assertEquals ( "muxer_resets " 				 + trans_stats.getInt(0) +
								  " decoder_resets " 			 +  trans_stats.getInt(1) +
								  " encoder_resets " 			 + trans_stats.getInt(2) +
								  " muxer_drops "    			 + trans_stats.getInt(3) +
								  " muxer_resets " 				 + trans_stats.getInt(4) + 
								  " raw_frame_drops " 			 + trans_stats.getInt(5) + 
								  " restarts " 					 + trans_stats.getInt(6) + 
								  " smoother_drops " 			 + trans_stats.getInt(7) + 
								  " src_cmp_frame_demux_drop "   + trans_stats.getInt(8) +
								  " src_cmp_frame_overflows " 	 + trans_stats.getInt(9) +
								  " connections "				 + streamConnections, 
		 
								  "muxer_resets " 				 + 0 + 
								  " decoder_resets "    		 +  0 +
								  " encoder_resets " 	   		 + 0 +
								  " muxer_drops "  				 + 0 +
								  " muxer_resets " 				 + 0 + 
								  " raw_frame_drops " 			 + 0 +
								  " restarts "  				 + 0 + 
								  " src_cmp_frame_demux_drop " 	 + 0 + 
								  " src_cmp_frame_overflows "    + 0 +
								  " connections "             	 + 0);
			
			// Checking if broadcaster has crashes while execution of the test.
			Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234", 
			login_ip,  "22",  "pidof zixi_broadcaster"));
		}
	}
}
