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

	protected JSONObject json = null;
	protected final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.124 Safari/537.36";
	private String tester = null;
	protected HttpURLConnection con;
	
	
	// It is very specific function is used to upload a server private key to a SPECIFIC feeder server - Such strong and disturbing rule comes from a Feeder server UI implementation.
	public String inserKeyToSpecificFeeder(String url, String id, String[] responseCookieContainer, String HOST, Object caller, String uiport, byte[] keyByteArray)
	{
		List<Byte>    bytesList = new ArrayList<>(); ;
//		StringBuilder mime = new StringBuilder(); 
//		StringBuilder endmime = new StringBuilder();
		
//		mime.append("------WebKitFormBoundaryKRWLCVBtxzUWnrpy\n");
//		mime.append("Content-Disposition: form-data; name=\"ssh_key_file\"; filename=\"private_ssh_key_to-localhost.localdomain.key\"\n");
//		mime.append("Content-Type: application/octet-stream\n");
//		mime.append("\nPOST /upload_ssh_key.htm HTTP/1.1\n");
//		mime.append("Host:" + HOST + ":" + uiport +"\n");
//		mime.append("Connection: keep-alive\n");
//		mime.append("Content-Length: 1919\n");
//		mime.append("Accept: */*\n");
//		mime.append("acsrf_d7421b18: ED6B19A376AC8929007B3F90DEBB203F\n");
//		mime.append("Origin: http://10.7.0.65:4200\n");
//		mime.append("X-Requested-With: XMLHttpRequest\n");
//		mime.append("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36\n");
//		mime.append("Content-Type: multipart/form-data; boundary=----WebKitFormBoundaryo6y5J7AvAOjL7qPY\n");
//		mime.append("Referer: http://"+ HOST + ":" + uiport +"/index.html\n");
//		mime.append("Accept-Encoding: gzip, deflate\n");
//		mime.append("Accept-Language: en-US,en;q=0.8,he;q=0.6,ru;q=0.4\n");
//		mime.append("Cookie: outputs_table_grouping_index=2; session-feeder-localhost.localdomain=6186CDF4DA1E57606AF28FB2DA05AA29; acsrf-feeder=acsrf_d7421b18%3DED6B19A376AC8929007B3F90DEBB203F;\n");
//
//		byte [] preambul =  mime.toString().getBytes();
	
//		for(byte b : preambul) {
//			bytesList.add(b);
//		}
	
		StringBuffer response = new StringBuffer();
		
		try {
			
			URL destUrl = new URL(url);  
			con = (HttpURLConnection) destUrl.openConnection();
			con	.setDoOutput( true );
			con.setRequestMethod("POST");
			// add request header
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
			
			DataOutputStream wr = new DataOutputStream( con.getOutputStream()); 
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
			
			// Temporary buffer.
			outputStream.write( keyByteArray );

			byte c[] = outputStream.toByteArray( );
			
			// Print a POST body to a stream.
			wr.write( c );
			wr.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				System.out.println(inputLine);
			}
			in.close();
			wr.close();
			
		} catch (Exception e) {
			String exceptionTest = e.getMessage();
			System.out.println("bug ------------- " + exceptionTest + "Request is "   + url );
		}
		finally {
			con.disconnect();
		}
		
		return response.toString();
	}

	public String sendGet(String url, String id, int mode,
			String[] responseCookieContainer, String HOST, Object caller,
			String uiport) {
		StringBuffer response = new StringBuffer();
		try {
			
			URL destUrl = new URL(url);
			con = (HttpURLConnection) destUrl.openConnection();
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

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

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
			
			
			if (mode == FEEDER_SSH_SERVER_STATUS) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				return json.getString("host");
			}
			
			if (mode == FEEDER_SSH_KEY_STATUS) {
				inputLine = response.toString();
				json = new JSONObject(inputLine);
				json = json.getJSONObject("server");
				return json.getInt("key_present") + "";
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
			

		} catch (Exception e) {
			String exceptionTest = e.getMessage();
			System.out.println("bug ------------- " + exceptionTest + "Request is "   + url );
		}
		finally {
			con.disconnect();
		}
		return response.toString();
	}

}