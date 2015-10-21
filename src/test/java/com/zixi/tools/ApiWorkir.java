package com.zixi.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.zixi.globals.Macros.*;


public class ApiWorkir {
	
	protected JSONObject json = null;
	protected final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36";
	private String tester = null;
	// HTTP GET request
	public String sendGet(String url, String id, int mode, String[] responseCookieContainer, String HOST, Object caller) {

		StringBuffer response = new StringBuffer();
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			// optional, default is GET
			con.setRequestMethod("GET");
			// add request header
			con.setRequestProperty("Host", HOST+":4444");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Referer",
					"http://"+ HOST +":4444/login.html");
			con.setRequestProperty(StringUtils.substringBetween(responseCookieContainer[0],"=","%3Dacsrf"), 
					   StringUtils.substringAfter(responseCookieContainer[0], "%3D")); 

			con.setRequestProperty("Cookie", responseCookieContainer[1] + "; " + responseCookieContainer[0] );

			int responseCode = con.getResponseCode();
			
			// System.out.println("\nSending 'GET' request to URL : " + url);
			// System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//System.out.println("+*+" + inputLine + "+*+");
			
			if (mode == PUSHMODE) 
			{
				inputLine = response.toString();
				int indx = inputLine.indexOf("(");
				inputLine = (inputLine.substring(indx + 1, inputLine.indexOf(");")));
				json = new JSONObject(inputLine);
				return json.get("msg").toString();	
			}
			
			if (mode == PUSHINMODE)
			{
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.get("msg").toString();	
			}
			
			if (mode == PULLMODE)
			{
				inputLine = response.toString();
				int indx = inputLine.indexOf("(");
				inputLine = (inputLine.substring(indx + 1, inputLine.indexOf(");")));
				json = new JSONObject(inputLine);
				System.out.println("Debug printing   -- " + json.get("msg").toString());
				return json.get("msg").toString();					
			}
			
			if (mode == UDPMODE)
			{
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				System.out.println("Debug printing   -- " + json.get("msg").toString());
				return json.get("msg").toString();	 
			}
			
			if (mode == UDPOUTMODE)
			{
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				tester = json.getString("msg");
				System.out.println("Debug printing   -- " + tester);
				if(tester.endsWith("Output " + id + " added."))
				{
        		 return tester = "good";
				}
			}
			
			if (mode == JSONMODE)
			{
				ArrayList<String> inputsStreamNames = new ArrayList();
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				JSONArray inputStreamsJsonArrayObj = json.getJSONArray("streams");
				int numberOfElementsInInputStreamsJsonArrayObj =  inputStreamsJsonArrayObj.length();
				for(int i = 0; i < numberOfElementsInInputStreamsJsonArrayObj; i++)
				{
					json = inputStreamsJsonArrayObj.getJSONObject(i);
					inputsStreamNames.add(json.get("id").toString());
				}
				tester = "good";
			}
			if (mode == 77)
			{	
        		 return inputLine = response.toString();
			}
			
			if (mode == PUSHOUTMODE)
			{
				String wholeResult; 
				String [] splittedResults;
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				System.out.println("Debug printing   -- " + json.get("msg").toString());
				wholeResult = json.get("msg").toString();
				splittedResults = wholeResult.split(",");
				return splittedResults[0];
			}
			
		} catch (Exception e) {

			System.out.println("bug -------------" + e.getMessage());
		}
		return  response.toString();
	}

}
