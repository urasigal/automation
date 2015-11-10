package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import java.util.ArrayList;

import org.testng.Reporter;

import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggable;
import com.zixi.tools.StreamStatisticAnalyzer;

public class ReceiverInputStatisticDriver extends BroadcasterLoggable implements
		TestDriver {

	protected ApiWorkir streamManipulator = new ApiWorkir();
	private ArrayList<Integer> bitrateList = new ArrayList();
	private StreamStatisticAnalyzer streamStatisticAnalyzer = new StreamStatisticAnalyzer();

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String id, String testduration) {
		int singleBitrateProbe = 0;
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		String streamId = streamManipulator.sendGet("http://" + login_ip + ":"
				+ uiport + "/in_streams.json?complete=1", id, RECEIVERIDMODE,
				responseCookieContainer, login_ip, this, uiport);
		try {
			Thread.currentThread().sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < Integer.parseInt(testduration); i++) 
		{
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			singleBitrateProbe =  Integer.parseInt(streamManipulator.sendGet("http://" + login_ip + ":" + uiport
					+ "/in_stats.json?id=" + streamId, id, RECEIVERSTATISTICMODE,
					responseCookieContainer, login_ip, this, uiport));
			if (singleBitrateProbe == 0)
			{
				return "zero bit rate is detected"; 
			}
		
			bitrateList.add(singleBitrateProbe);
		}
		
		int[] statArray = streamStatisticAnalyzer.getMaxMinAvg(bitrateList);
		Reporter.log("Max bitrate  is " + statArray[0] + "");
		Reporter.log("Min bitrate is " + statArray[1] + "");
		Reporter.log("Average bitrate is " + statArray[2] + "");
		return "tested";
	}
}
