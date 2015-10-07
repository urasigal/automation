package com.zixi.tools;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONObject;


public class StreamStatisticAnalyzer {
	
	
	public int getStatBitrate(JSONObject statisticJson)
	{
		return statisticJson.getJSONObject("net").getInt("bitrate");
	}
	
	
	
	public int[] getMaxMinAvg(ArrayList<Integer> stat)
	{
		int avg = 0;
		Collections.sort(stat);
		int max = stat.get(stat.size() - 1);
		int min = stat.get(0);
		for (int i = 0; i < stat.size(); i++)
		{
			avg += stat.get(i);
		}
		avg = avg / stat.size();
		int[] statArray = {max,min,avg};
		return statArray;
	}
}
