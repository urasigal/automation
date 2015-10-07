package com.zixi.drivers;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;

import com.zixi.tools.BroadcasterInitialSecuredLogin;
import com.zixi.tools.BroadcasterInputStatisticHelper;
import com.zixi.tools.BroadcasterLoggable;
import com.zixi.tools.StreamStatisticAnalyzer;


public class BroadcasterInputStatisticSingleStreamDriver extends BroadcasterLoggable implements TestDriver {
	
	private BroadcasterInputStatisticHelper broadcasterInputStatisticHelper = new BroadcasterInputStatisticHelper();
	private JSONObject statisitcJson;
	protected StreamStatisticAnalyzer streamStatisticAnalyzer = new StreamStatisticAnalyzer();
	
	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String FUNCTION = "/input_stream_stats.json?"; // API function get all outputs
	

	public String testStatistic(String userName, String userPass, String host, String loin_ip, String uiport, String id, String testduration)
	{
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(HTTP + loin_ip + ":" + uiport + "/login.htm", userName , userPass, loin_ip, uiport);
		ArrayList<Integer> bitRateList = new ArrayList<Integer>();
		
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Object of this class will be responsible to sending the WEB API request. 
		
		for (int i = 0; i < Integer.parseInt(testduration); i++) 
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statisitcJson = broadcasterInputStatisticHelper.sendGet(HTTP + loin_ip
					+ ":" + uiport + FUNCTION + "id" + "=" + id + "&" +
					(responseCookieContainer[0].replaceAll("%3D", "=")).replaceAll("acsrf=", ""),loin_ip,responseCookieContainer);
			System.out.println(statisitcJson.toString());
			int bitrate = streamStatisticAnalyzer.getStatBitrate(statisitcJson);
			if (bitrate == 0)
			{
				return null;
			}
			else
				bitRateList.add(bitrate);
		}
		
		int statResults[] = streamStatisticAnalyzer.getMaxMinAvg(bitRateList);
		Reporter.log("<h4>Max bitrate is " + statResults[0] +"</h4>");
		Reporter.log("<h4>Min bitrete is " + statResults[1] + "</h4>");
		Reporter.log("<h4>Average bitrate is " + statResults[2] +"</h4>");
		return "good";
	}
}
