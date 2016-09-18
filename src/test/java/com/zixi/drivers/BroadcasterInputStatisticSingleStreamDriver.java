package com.zixi.drivers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterInitialSecuredLogin;
import com.zixi.tools.BroadcasterInputStatisticHelper;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.StreamStatisticAnalyzer;

public class BroadcasterInputStatisticSingleStreamDriver extends BroadcasterLoggableApiWorker implements TestDriver {

	private BroadcasterInputStatisticHelper broadcasterInputStatisticHelper = new BroadcasterInputStatisticHelper();
	private JSONObject statisitcJson;
	protected StreamStatisticAnalyzer streamStatisticAnalyzer = new StreamStatisticAnalyzer();

	final private static String HTTP = "http://";
	final private static String FUNCTION = "/input_stream_stats.json?"; 
	
	public String testStatistic(String userName, String userPass, String host,String loin_ip, String uiport, String id, String testduration) throws Exception {
		
	testParameters = new TestParameters("userName:" + userName, "userPass:" + userPass, "host:" + host, "loin_ip:" + loin_ip, "uiport:"
	+ uiport, "id:" + id, "testduration:" + testduration);
		
	responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(HTTP + loin_ip + ":" + uiport + "/login.htm", userName, userPass, loin_ip, uiport);
		
	ArrayList<Integer> bitRateList = new ArrayList<Integer>();

		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < Integer.parseInt(testduration) * 2; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Returns a json response to a "/input_stream_stats.json?" request.
			statisitcJson = broadcasterInputStatisticHelper.sendGet(HTTP
					+ loin_ip + ":" + uiport + FUNCTION + "id" + "=" + id,
					loin_ip, responseCookieContainer);
			// debug printing System.out.println(statisitcJson.toString());
			
			int bitrate = streamStatisticAnalyzer.getStatBitrate(statisitcJson);
			if (bitrate == 0) {
				return "On of the bit rate API probing showed zero bit rete";
			} else
				bitRateList.add(bitrate);
		}

		int statResults[] = streamStatisticAnalyzer.getMaxMinAvg(bitRateList);
		
		Reporter.log("Max bitrate is " + statResults[0]);
		Reporter.log("Min bitrete is " + statResults[1]);
		Reporter.log("Average bitrate is " + statResults[2]);
		return "good";
	}
}
