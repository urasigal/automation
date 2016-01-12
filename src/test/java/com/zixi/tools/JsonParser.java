package com.zixi.tools;

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
		for (int i = 0; i < numberOfElementsInInputStreamsJsonArrayObj; i++) {
			json = inputStreamsJsonArrayObj.getJSONObject(i);
			
			tmp = json.get("id").toString();
			if(tmp.equals(id))
			{
				return json.getString("source");
			}
		}
		
		return "";
	}
	

}
