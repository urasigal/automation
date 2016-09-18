package com.zixi.load.drivers;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.zixi.drivers.tools.Macros.*;

import com.zixi.drivers.FFMPEGImageStatisticTestDriver;
import com.zixi.drivers.drivers.BroadcasterInputStreamDriver;
import com.zixi.drivers.tools.DriverStuff;
import com.zixi.drivers.tools.TestDriver;

import java.util.ArrayList;
import java.util.Random;

public class BroadcasterRundomInputStreamDriver extends DriverStuff implements TestDriver{
	
	protected BroadcasterInputStreamDriver broadcasterInputStreamDriver = new BroadcasterInputStreamDriver();
	
	public String testIMPL(String login_ip, String userName, String userPassword, String uiport, String name) throws InterruptedException, ExecutionException
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPassword, login_ip, uiport);
		
		// Retrieve input stream names from a broadcaster server.
		String[] streamsNames  = broadcasterInputStreamDriver.retrieveAllInputStreamsFromBroadcaser(login_ip, userName, userPassword, uiport);
		int inputStreamNumbers = streamsNames.length;
		
		
		// get all outputs Json from broadcaster
		String response = apiworker.sendGet("http://" + login_ip + ":" + uiport + "/zixi/outputs.json", "", 77, responseCookieContainer, login_ip, this, uiport );
		
		JSONObject responseJson = new JSONObject(response.toString());
		JSONArray outputStreamsArray = responseJson.getJSONArray("outputs");
		String internalStreamID = null;
		String internalStreamName = null;
		String streamName = null;
		
		// Walk through all outputs and find an id by an output name.
		for (int i = 0; i < outputStreamsArray.length(); i++) 
		{
			//System.out.println("before");
			JSONObject outputStream = outputStreamsArray.getJSONObject(i);
		    streamName = outputStream.getString("name");
		    if(streamName.equals(name))
		    {
		    	internalStreamID = outputStream.getString("id");
		    	internalStreamName = outputStream.getString("stream_id");
		    }
		  }
		
		Random rand = new Random();
		FFMPEGImageStatisticTestDriver fFMPEGImageStatisticTestDriver = new FFMPEGImageStatisticTestDriver();
		String testResult;
		
		ArrayList<String> ffmpegResults = new ArrayList<>();
		
		for(int i = 0 ; i < TEST_ATTEMPT_QUNTYTY ; i++)
		{
			int randNumIndex = rand.nextInt((inputStreamNumbers));
			apiworker.sendGet("http://" + login_ip + ":" + uiport + "/zixi/redirect_client.json?id=" + internalStreamID + "&stream=" + streamsNames[randNumIndex] + 
			"&update-remote=1","", RECEIVERMODE, responseCookieContainer, login_ip, this, uiport);
			
			ffmpegResults.add(fFMPEGImageStatisticTestDriver.testStatistic());
		}
		if (ffmpegResults.contains("bad"))
			return "bad";
		return "good";
	} 
	
//		public static void main(String[] args) throws InterruptedException, ExecutionException {
//		BroadcasterRundomInputStreamDriver broadcasterRundomInputStreamDriver = new BroadcasterRundomInputStreamDriver();
//		broadcasterRundomInputStreamDriver.testIMPL("10.7.0.63", "admin", "1234", "4444", "best");
//	}
}
