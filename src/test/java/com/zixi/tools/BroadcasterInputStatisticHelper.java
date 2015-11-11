package com.zixi.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;


public class BroadcasterInputStatisticHelper {
	private JSONObject json = null;
	private JSONObject net = null;
	protected final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36";

	// HTTP GET request
	public JSONObject sendGet(String url, String HOST, String[] responseCookieContainer) {
		try {
			/// HTTP connection setup
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional, default is GET
			con.setRequestMethod("GET");
			// add request header
			con.setRequestProperty("Host", HOST+":4444");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Referer",
					"http://"+ HOST +":4444/login.html");
			
			con.setRequestProperty(StringUtils.substringBetween(
					
					responseCookieContainer[0], "=", "%"), StringUtils
						.substringAfter(responseCookieContainer[0], "%3D"));

				con.setRequestProperty("Cookie", responseCookieContainer[1] + "; "
						+ responseCookieContainer[0] );
	//////////////////////////////////////////////////////////
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuffer response = new StringBuffer();
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			json = new JSONObject(response.toString());
		} catch (Exception e) {
			System.out.println("Error message is " + e.getMessage());
		}
		return json;
	}
}
