package com.zixi.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.zixi.drivers.ReceiverInputStatisticDriver;
import com.zixi.testing.ReceiverInputStatisticTest;

import static com.zixi.globals.Macros.*;

public class ApiWorkir {

	protected JSONObject        json = null;
	protected final String      USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36";
	private String              tester = null;
	protected HttpURLConnection con;
	protected int               debugLineNumber = 0;
	protected StringBuffer 		testFlowDescriptor;
	// default constructor.
	public ApiWorkir() {}
	
	
	public ApiWorkir(StringBuffer testFlowDescriptor) {
		this.testFlowDescriptor = testFlowDescriptor;
	}
	
	
	// It is very specific function is used to upload a server private key to a SPECIFIC feeder server - Such strong and disturbing rule comes from a Feeder server UI implementation.
	public String inserKeyToSpecificFeeder(String url, String id, String[] responseCookieContainer, String HOST, Object caller, String uiport, byte[] keyByteArray)
	{
		List<Byte>   bytesList       = new ArrayList<>();
					 debugLineNumber = 36;
	
		StringBuffer response = new StringBuffer();
		
		try {
			debugLineNumber = 42;
			URL destUrl = new URL(url);  
			con = (HttpURLConnection) destUrl.openConnection();
			con	.setDoOutput( true );
			//  Setup parameters and general request properties that you may need before connecting.
			///////////////////////////////////////////////////////////////////////////////////////
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("Pragma","no-cache");
			con.setRequestProperty("Cache-Control","no-cache");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("Host", HOST + ":" + uiport);
			con.setRequestProperty("Origin", "http://" + HOST + ":" + uiport);
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Content-Type", "multipart/form-data" + ";" + " boundary=----WebKitFormBoundary8jMUwKeMgBKtvuqv");
			con.setRequestProperty("Referer", "http://" + HOST + ":" + uiport + "/index.html");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.8,he;q=0.6,ru;q=0.4");
			con.setRequestProperty(StringUtils.substringBetween(responseCookieContainer[0], "=", "%"), StringUtils.substringAfter(responseCookieContainer[0], "%3D"));
			con.setRequestProperty("Cookie", responseCookieContainer[1] + "; "+ responseCookieContainer[0]);
			//////////////////////////////////////////////////////////////////////////////////////////////////
			debugLineNumber = 63;
			DataOutputStream wr = new DataOutputStream( con.getOutputStream()); 
			debugLineNumber = 65;
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
			debugLineNumber = 75;
			// Temporary buffer.
			outputStream.write( keyByteArray );

			byte c[] = outputStream.toByteArray( );
			
			// Print a POST body to a stream.
			wr.write( c );
			wr.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				System.out.println(inputLine);
			}
			in.close();
			wr.close();
			
		} catch (Exception e) {
			String exceptionTest = e.getMessage();
			System.out.println("bug ------------->>> " + exceptionTest + "Request is "   + url );
		}
		finally {
			con.disconnect();
		}
		
		return response.toString();
	}

	public String sendGet(String url, String id, int mode,String[] responseCookieContainer, String HOST, Object caller, String uiport) {
		debugLineNumber = 98;
		StringBuffer response = new StringBuffer();
		try {
			URL destUrl = new URL(url);
			debugLineNumber = 102;
			con = (HttpURLConnection) destUrl.openConnection();
			// Setup parameters and general request properties that you may need before connecting.
			///////////////////////////////////////////////////////////////////////////////////////
			con.setReadTimeout(60000);
			debugLineNumber    = 107;
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("Host", HOST + ":" + uiport);
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Referer", "http://" + HOST + ":" + uiport + "/index.html");
			con.setRequestProperty(StringUtils.substringBetween(responseCookieContainer[0], "=", "%"), StringUtils.substringAfter(responseCookieContainer[0], "%3D"));
			debugLineNumber    = 116;
			con.setRequestProperty("Cookie", responseCookieContainer[1] + "; " + responseCookieContainer[0]);
			///////////////////////////////////////////////////////////////////////////////////////
			debugLineNumber = 127;
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			debugLineNumber = 120;
			String inputLine = "";
			while ((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
			in.close();
			
			switch(mode)
			{
			  case RECEIVERIDMODE:		 		inputLine = response.toString();
				  								json = new JSONObject(inputLine);
												JSONArray streams = json.getJSONArray("streams");
												for (int i = 0; i < streams.length(); i++) 
												{
													json = streams.getJSONObject(i);
								
													if (json.get("name").toString().equals(id)) 
													{
														System.out.println(json.get("name").toString());
														return json.get("id").toString();
													}
												}
												System.out.println("no such a stream");
												return "no such a stream";
									
				case FEEDER_SSH_SERVER_STATUS:  inputLine = response.toString();
				   							    json = new JSONObject(inputLine);
				   							    return json.getString("host");
				   							   
				   							
				case FEEDER_SSH_KEY_STATUS:     inputLine = response.toString();   
												json = new JSONObject(inputLine);
											    json = json.getJSONObject("server");
											    return json.getInt("key_present") + "";
				
				case PUSHMODE:					inputLine = response.toString();
												int indx = inputLine.indexOf("(");
												inputLine = (inputLine.substring(indx + 1,inputLine.indexOf(");")));
												json = new JSONObject(inputLine);
												return json.get("msg").toString();
				
				case ADD_TRANSCODER_PROFILE: 	inputLine = response.toString();
												json = new JSONObject(inputLine);
												return json.get("msg").toString();
				
				case PUSHINMODE:				inputLine = response.toString();
												System.out.println("Debug printing ------>>> " + inputLine);
												json = new JSONObject(inputLine);
												return json.get("msg").toString();
				
				case RECEIVER_UDP_OUT_MODE: 	inputLine = response.toString();
												json = new JSONObject(inputLine);
												return json.get("message").toString();
				
				case PULLMODE: 					inputLine = response.toString();
												int ind = inputLine.indexOf("(");
												inputLine = (inputLine.substring(ind + 1,
														inputLine.indexOf(");")));
												json = new JSONObject(inputLine);
												return json.get("msg").toString();
				
				case UDPMODE: 					inputLine = response.toString();
												json = new JSONObject(inputLine);
												return json.get("msg").toString();
				
				case RECEIVERMODE: 				inputLine = response.toString();
												json = new JSONObject(inputLine);
												return json.get("message").toString();
				
				case RECEIVERDELETIONMODE:		inputLine = response.toString();
												json = new JSONObject(inputLine);
												return json.get("message").toString();
												
				case RECEIVERSTATISTICMODE: 	inputLine = response.toString();
												json = new JSONObject(inputLine);
												return json.getJSONObject("data").get("bitrate").toString();						
				
				case UDPOUTMODE:				inputLine = response.toString();
												json = new JSONObject(inputLine);
												tester = json.getString("msg");
												if (tester.endsWith("Output " + id + " added."))
												{
													return tester = "Output " + id + " added.";
												}
				
				case JSONMODE:					ArrayList<String> inputsStreamNames = new ArrayList();
												inputLine = response.toString();
												json = new JSONObject(inputLine);
												JSONArray inputStreamsJsonArrayObj = json.getJSONArray("streams");
												int numberOfElementsInInputStreamsJsonArrayObj = inputStreamsJsonArrayObj.length();
												for (int i = 0; i < numberOfElementsInInputStreamsJsonArrayObj; i++) {
													json = inputStreamsJsonArrayObj.getJSONObject(i);
													inputsStreamNames.add(json.get("id").toString());
												}
												tester = "good";
				
				case SET_RTMMP_AUTO_REMOTE: 	inputLine = response.toString();
												json = new JSONObject(inputLine);
												return json.getJSONObject("http_outs").getInt("rtmp_auto_out") + "";
				
				case 77: 						return inputLine = response.toString();
				
				case PUSHOUTMODE: 			    String wholeResult;
												String[] splittedResults;
												inputLine = response.toString();
												json = new JSONObject(inputLine);
												System.out.println("Debug printing   -- " + json.get("msg").toString());
												wholeResult = json.get("msg").toString();
												splittedResults = wholeResult.split(",");
												return splittedResults[0];
			}

		} catch (Exception e) {
			String exceptionTest = e.getMessage();
			System.out.println("bug ------------->>> " + exceptionTest + " Request is "   + url + " \nLine Number is " +  debugLineNumber);
			System.out.println("Exception type is " + e.getClass());
		}
		finally {
			con.disconnect();
		}
		return response.toString();
	}

}