package com.zixi.load.testing;

import java.util.concurrent.ExecutionException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zixi.drivers.drivers.BroadcaserAllInputStreamDeletorDriver;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.load.drivers.BroadcasterMultipleRtmpPushDriver;
import com.zixi.testing.BaseTest;

public class BroadcasterMultipleRtmpPushTest extends BaseTest{

	// Instantiate a test driver class
	// Will run before any test method in this class
	@Parameters({"testid"})
	@BeforeClass
	public void testInit(String testid) {
		testDriver = new BroadcasterMultipleRtmpPushDriver();
		this.testid = testid;
	}
	
	// This are a test parameters that will be got from a test suite xml configuration file.
	@Parameters({"login_ipBX1", "login_ipBX2", "userNameBX1","userNameBX2", "userPasswordBX1", "userPasswordBX2", "uiportBX1", "uiportBX2", "typeBX1", "name", "stream", "matrixBX1", "url",
	"url_alt", "rtmp_stream", "user", "bandwidth", "latency", "reconnect", "sendfi","disconnect_low_br", "static_latency", "dec_type", 
	"dec_key", "password", "typeBX2", "id", "matrixBX2", "max_outputs", "mcast_out", "time_shift", "old", "fast_connect", "kompression", "enc_type",
	"enc_key", "rec_history", "rec_duration", "rtmp_url", "rtmp_name", "rtmp_user", "number_of_streams", "testid"})
	@Test
	public void broadcasterRtmpPushLoadTest(String login_ipBX1, String login_ipBX2, String userNameBX1,String userNameBX2, String userPasswordBX1, String userPasswordBX2, String uiportBX1, 
	String uiportBX2, String typeBX1, String name, String stream, String matrixBX1, String url,
	String url_alt, String rtmp_stream, String user, String bandwidth, String latency, String reconnect, String sendfi,String disconnect_low_br, 
	String static_latency, String dec_type, String dec_key, String password, String typeBX2, String id, String matrixBX2, 
	String max_outputs, String mcast_out, String time_shift,String old,
	String fast_connect, String kompression,String enc_type, String enc_key, String rec_history, String rec_duration, String rtmp_url,
	String rtmp_name, String rtmp_user, String number_of_streams, String testid) throws Exception {	
		// Get broadcaster PID in the beginning of the test.
		sutProcessId		  = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ipBX1,  "22",  "pidof zixi_broadcaster");
		
		// This is a special case because of using two broadcasters in a single test.
		String sutProcessIdBX2 = BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ipBX2,  "22",  "pidof zixi_broadcaster");
		
		// Retrieve the product version. Parameters: 1 - host, 2 - user interface port, 3 - product login name, 4 - product login password.
		this.version = productAboutDriver.getBroadcasterVersion(login_ipBX1, uiportBX1, userNameBX1, userPasswordBX1);
		InnerHelper innerHelper = new InnerHelper();		
		
		testLinktestParameters = innerHelper.saveParameters(login_ipBX1, login_ipBX2, userNameBX1, userNameBX2, userPasswordBX1, userPasswordBX2, uiportBX1, uiportBX2, typeBX1, name, stream, matrixBX1,
		url, url_alt, rtmp_stream, user, bandwidth, latency, reconnect, sendfi, disconnect_low_br, static_latency, dec_type,
		dec_key, password, typeBX2, sutProcessIdBX2, matrixBX2, max_outputs, mcast_out, time_shift, old, fast_connect, kompression, enc_type, 
		enc_key, rec_history, rec_duration, rtmp_url, rtmp_name, rtmp_user, number_of_streams, testid);
		
		driverReslut = ((BroadcasterMultipleRtmpPushDriver) testDriver).testIMPL(login_ipBX1,  login_ipBX2,  userNameBX1, userNameBX2,  userPasswordBX1,  userPasswordBX2,  uiportBX1, 
		 uiportBX2,  typeBX1,  name,  stream,  matrixBX1,  url, url_alt,  rtmp_stream,  user,  bandwidth,  latency,  reconnect,  sendfi, disconnect_low_br, 
		 static_latency,  dec_type,  dec_key,  password,  typeBX2,  id,  matrixBX2, max_outputs,  mcast_out,  time_shift, old,
		 fast_connect,  kompression, enc_type,  enc_key,  rec_history,  rec_duration,  rtmp_url, rtmp_name,  rtmp_user, number_of_streams);
		
		// Test logic: The test case checks if the number of created output streams is equal to the number of created input 
		// streams and in turn it equal to the number of desired test.
		Assert.assertEquals(driverReslut.getResult(), number_of_streams);
		
		Assert.assertEquals(sutProcessId, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ipBX1,  "22",  "pidof zixi_broadcaster"));
		Assert.assertEquals(sutProcessIdBX2, BroadcaserSingleOutputStreamDeletionDriver.getPid("root",  "zixiroot1234",  login_ipBX2,  "22",  "pidof zixi_broadcaster"));
	}
	
	private class InnerHelper{
		
		private String saveParameters(String login_ipBX1, String login_ipBX2, String userNameBX1,String userNameBX2, String userPasswordBX1, String userPasswordBX2, String uiportBX1, 
		String uiportBX2, String typeBX1, String name, String stream, String matrixBX1, String url,
		String url_alt, String rtmp_stream, String user, String bandwidth, String latency, String reconnect, String sendfi,String disconnect_low_br, 
		String static_latency, String dec_type, String dec_key, String password, String typeBX2, String id, String matrixBX2, 
		String max_outputs, String mcast_out, String time_shift,String old,
		String fast_connect, String kompression,String enc_type, String enc_key, String rec_history, String rec_duration, String rtmp_url,
		String rtmp_name, String rtmp_user, String number_of_streams, String testid){
			
			return buildTestParametersString(new String[] {"login_ipBX1", "login_ipBX2", "userNameBX1","userNameBX2", "userPasswordBX1", "userPasswordBX2", "uiportBX1", 
			"uiportBX2", "typeBX1", "name", "stream", "matrixBX1", "url",
			"url_alt", "rtmp_stream", "user", "bandwidth", "latency", "reconnect", "sendfi","disconnect_low_br", "static_latency", "dec_type", 
			"dec_key", "password", "typeBX2", "id", "matrixBX2", "max_outputs", "mcast_out", "time_shift", "old", "fast_connect", "kompression", "enc_type",
			"enc_key", "rec_history", "rec_duration", "rtmp_url", "rtmp_name", "rtmp_user", "number_of_streams", "testid"}, 
			 new String[] { login_ipBX1,  login_ipBX2,  userNameBX1, userNameBX2,  userPasswordBX1,  userPasswordBX2,  uiportBX1, 
			 uiportBX2,  typeBX1,  name,  stream,  matrixBX1,  url, url_alt,  rtmp_stream,  user,  bandwidth,  latency,  reconnect,  sendfi, disconnect_low_br, 
			 static_latency,  dec_type,  dec_key,  password,  typeBX2,  id,  matrixBX2, max_outputs,  mcast_out,  time_shift, old,
			 fast_connect,  kompression, enc_type,  enc_key,  rec_history,  rec_duration,  rtmp_url, rtmp_name,  rtmp_user,  number_of_streams, testid});
		}
	}
}
