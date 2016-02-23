package com.zixi.tools;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
	
	
	public String getSourceInputIpById(String streamsJson, String id)
	{
		JSONObject json = null;
		String tmp;
		json = new JSONObject(streamsJson);
		JSONArray inputStreamsJsonArrayObj = json.getJSONArray("streams");
		int numberOfElementsInInputStreamsJsonArrayObj = inputStreamsJsonArrayObj.length();
		for (int i = 0; i < numberOfElementsInInputStreamsJsonArrayObj; i++) 
		{
			json = inputStreamsJsonArrayObj.getJSONObject(i);
			
			tmp = json.get("id").toString();
			if(tmp.equals(id))
			{
				return json.getString("source");
			}
		}
		
		return "";
	}
	
	
	public String getSourceOutputIpById(String streamsJson, String id)
	{
		JSONObject json = null;
		String tmp;
		int bitrate;
		json = new JSONObject(streamsJson);
		JSONArray outputsStreamsJsonArrayObj = json.getJSONArray("outputs");
		
		int numberOfElementsInOutputStreamsJsonArrayObj = outputsStreamsJsonArrayObj.length();
		for (int i = 0; i < numberOfElementsInOutputStreamsJsonArrayObj; i++) 
		{
			json = outputsStreamsJsonArrayObj.getJSONObject(i);
			
			tmp = json.get("stream_id").toString();
			bitrate = json.getInt("bitrate");
			if(tmp.equals(id) && bitrate != 0) 
			{
				return json.getString("ip");
			}
		}
		
		return "";
	}
	
	public static List<String> retriveIpAddresses(String streamsJson)
	{
		List<String> ipList = new ArrayList<String>();
		
		JSONObject json = null;
		
		json = new JSONObject(streamsJson);
		
		JSONArray netDevices = json.getJSONArray("devices");
		
		int numOfDev = netDevices.length();
		
		for (int i = 0; i < numOfDev; i++) 
		{
			json = netDevices.getJSONObject(i);
			
			JSONArray netInterface = json.getJSONArray("connections");
			
			int interfaceLangth = netInterface.length();
			
			for(int j = 0 ; j < interfaceLangth; j ++ )
			{
				json = netInterface.getJSONObject(j);
				ipList.add(json.getString("ip"));
			} 
		}
		return ipList;
	}
	
	public static String getVodSettings(String streamsJson)
	{
		JSONObject json = null;
		json = new JSONObject(streamsJson);
		json = json.getJSONObject("file_transfer");
		int auto_index   = json.getInt("auto_index");
		int progressive  = json.getInt("progressive");
		
		return auto_index + "&" + progressive ;
	}
	
	public static String receiverInputGetStreamIdByname(String streamsJson, String streamName)
	{
		JSONObject json = null;
		String id = null;
		json = new JSONObject(streamsJson);
		JSONArray inputStreams = json.getJSONArray("streams");
		
		int numOfStreams = inputStreams.length();
		for (int i = 0; i < numOfStreams; i++) 
		{
			json = inputStreams.getJSONObject(i);
			if(json.getString("name").equals(streamName))
			{
				id = json.getString("id");
				break;
			}
		}
		return id;
	}
	
	
	public static String receiverOutputGetStreamIdByname(String streamsJson, String streamName)
	{
		JSONObject json = null;
		String id = null;
		json = new JSONObject(streamsJson);
		JSONArray inputStreams = json.getJSONArray("streams");
		
		int numOfStreams = inputStreams.length();
		for (int i = 0; i < numOfStreams; i++) 
		{
			json = inputStreams.getJSONObject(i);
			if(json.getString("name").equals(streamName))
			{
				id = json.getString("out_id");
				break;
			}
		}
		return id;
	}
	
	
	public static int getInputStreamBitrate(String streamsJson, String streamName)
	{
		JSONObject json = null;
		String id = null;
		json = new JSONObject(streamsJson);
		json = json.getJSONObject("net");
		return json.getInt("bitrate");
	}
	
	
	
	

	public static String getBroadcasterVersion(String streamsJson)
	{
		JSONObject json = null;
		String id = null;
		json = new JSONObject(streamsJson);
		return json.getInt("version_major") + "." + json.getInt("version_minor") + "." + json.getInt("version_minor2") 
				+ "." + json.getInt("version_build") + "." + json.getString("platform"); 
	}

}
