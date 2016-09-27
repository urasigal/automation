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
import java.util.Collections;
import java.util.Random;

public class BroadcasterRundomInputStreamDriver extends DriverStuff implements TestDriver{
	
	protected BroadcasterInputStreamDriver broadcasterInputStreamDriver = new BroadcasterInputStreamDriver();
	protected StringBuffer testFlowDescriptor;
	
	// Default constructor.
	public BroadcasterRundomInputStreamDriver(){}
	
	public BroadcasterRundomInputStreamDriver(StringBuffer testFlowDescriptor){
		this.testFlowDescriptor = testFlowDescriptor;
	}
	
	public String testIMPL(String login_ip, String userName, String userPassword, String uiport, String name) throws InterruptedException, ExecutionException
	{
		testFlowDescriptor.append(" \n Begin driver... ");
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPassword, login_ip, uiport);
		testFlowDescriptor.append(" \n Logging to broadcaster... ");
		
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
		
		testFlowDescriptor.append(" Number of a random atempts is " + TEST_ATTEMPT_QUNTYTY);
		
		for(int i = 0 ; i < TEST_ATTEMPT_QUNTYTY ; i++)
		{
			int randNumIndex = rand.nextInt((inputStreamNumbers));
			
			// Switch input stream randomly.
			apiworker.sendGet("http://" + login_ip + ":" + uiport + "/zixi/redirect_client.json?id=" + internalStreamID + "&stream=" + streamsNames[randNumIndex] + 
			"&update-remote=1", "", RECEIVERMODE, responseCookieContainer, login_ip, this, uiport);
			
			// Simulate delay.
			Thread.sleep(5000); 
			
			String results = fFMPEGImageStatisticTestDriver.testStatistic(); 
			
			if (results.equals("bad"))
			{
				testFlowDescriptor.append(" \n Issue has been detected: bad FFMPEG response, stream name " + streamsNames[randNumIndex]);
			}
			
			ffmpegResults.add(results);
		}
		
		float ratio = Collections.frequency(ffmpegResults, "bad");
		testFlowDescriptor.append(" \n Percent of success " +  (100 - ((ratio * 100)/TEST_ATTEMPT_QUNTYTY)));
		
		if (ffmpegResults.contains("bad"))
			return "bad";
		return "good";
	} 
	
//		public static void main(String[] args) throws InterruptedException, ExecutionException {
//		BroadcasterRundomInputStreamDriver broadcasterRundomInputStreamDriver = new BroadcasterRundomInputStreamDriver();
//		broadcasterRundomInputStreamDriver.testIMPL("10.7.0.63", "admin", "1234", "4444", "best");
//	}
}
