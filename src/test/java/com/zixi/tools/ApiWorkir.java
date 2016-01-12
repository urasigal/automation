package com.zixi.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.zixi.drivers.ReceiverInputStatisticDriver;
import com.zixi.testing.ReceiverInputStatisticTest;

import static com.zixi.globals.Macros.*;

public class ApiWorkir {

	protected JSONObject json = null;
	protected final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36";
	private String tester = null;
	protected HttpURLConnection con;
	// HTTP GET request
	public String sendGet(String url, String id, int mode,
			String[] responseCookieContainer, String HOST, Object caller,
			String uiport) {

		StringBuffer response = new StringBuffer();
		try {
			
			URL obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			// optional, default is GET
			con.setRequestMethod("GET");
			// add request header
			con.setRequestProperty("Accept",
					"application/json, text/javascript, */*; q=0.01");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("Host", HOST + ":" + uiport);
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Referer", "http://" + HOST + ":" + uiport
					+ "/index.html");

			con.setRequestProperty(StringUtils.substringBetween(responseCookieContainer[0], "=", "%"), StringUtils.substringAfter(responseCookieContainer[0], "%3D"));

			con.setRequestProperty("Cookie", responseCookieContainer[1] + "; "
					+ responseCookieContainer[0]);

			//int responseCode = con.getResponseCode();

			// System.out.println("\nSending 'GET' request to URL : " + url);
			// System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// System.out.println("+*+" + inputLine + "+*+");

			if (mode == RECEIVERIDMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				JSONArray streams = json.getJSONArray("streams");

				for (int i = 0; i < streams.length(); i++) {
					json = streams.getJSONObject(i);

					if (json.get("name").toString().equals(id)) {
						System.out.println(json.get("name").toString());
						return json.get("id").toString();
					}
				}
				System.out.println("no such a stream to delete");
				return "no such a stream to delete";
			}

			if (mode == PUSHMODE) {
				inputLine = response.toString();
				int indx = inputLine.indexOf("(");
				inputLine = (inputLine.substring(indx + 1,
						inputLine.indexOf(");")));
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}

			
			if (mode == ADD_TRANSCODER_PROFILE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}
			
			if (mode == PUSHINMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}
			
			if (mode == RECEIVER_UDP_OUT_MODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("message").toString();
			}
			

			if (mode == PULLMODE) {
				inputLine = response.toString();
				int indx = inputLine.indexOf("(");
				inputLine = (inputLine.substring(indx + 1,
						inputLine.indexOf(");")));
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}

			if (mode == UDPMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("msg").toString();
			}

			if (mode == RECEIVERMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("message").toString();
			}

			if (mode == RECEIVERDELETIONMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("message").toString();
			}

			// under construction
			if (mode == RECEIVERSTATISTICMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.getJSONObject("data").get("bitrate").toString();
			}

			if (mode == UDPOUTMODE) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				tester = json.getString("msg");
				if (tester.endsWith("Output " + id + " added.")) {
					return tester = "good";
				}
			}

			if (mode == JSONMODE) {
				ArrayList<String> inputsStreamNames = new ArrayList();
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				JSONArray inputStreamsJsonArrayObj = json
						.getJSONArray("streams");
				int numberOfElementsInInputStreamsJsonArrayObj = inputStreamsJsonArrayObj
						.length();
				for (int i = 0; i < numberOfElementsInInputStreamsJsonArrayObj; i++) {
					json = inputStreamsJsonArrayObj.getJSONObject(i);
					inputsStreamNames.add(json.get("id").toString());
				}
				tester = "good";
			}
			
			if(mode == SET_RTMMP_AUTO_REMOTE)
			{
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.getJSONObject("http_outs").getInt("rtmp_auto_out") + "";
				
			}
			
			if (mode == 77) {
				return inputLine = response.toString();
			}

			if (mode == PUSHOUTMODE) {
				String wholeResult;
				String[] splittedResults;
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				System.out.println("Debug printing   -- "
						+ json.get("msg").toString());
				wholeResult = json.get("msg").toString();
				splittedResults = wholeResult.split(",");
				return splittedResults[0];
			}
			
//			if (mode == FIND_SOURCE_IP_BX)
//			{
//				String tmp;
//				inputLine = response.toString();
//				json = new JSONObject(inputLine);
//				JSONArray inputStreamsJsonArrayObj = json
//						.getJSONArray("streams");
//				int numberOfElementsInInputStreamsJsonArrayObj = inputStreamsJsonArrayObj
//						.length();
//				for (int i = 0; i < numberOfElementsInInputStreamsJsonArrayObj; i++) {
//					json = inputStreamsJsonArrayObj.getJSONObject(i);
//					
//					tmp = json.get("id").toString();
//					if(tmp.equals(anObject))
//				}
//				
//			}

		} catch (Exception e) {

			System.out.println("bug -------------" + e.getMessage());
		}
		finally {
			con.disconnect();
		}
		return response.toString();
	}
}