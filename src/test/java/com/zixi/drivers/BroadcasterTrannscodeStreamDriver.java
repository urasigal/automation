package com.zixi.drivers;

import org.json.JSONArray;
import org.json.JSONObject;
import static com.zixi.globals.Macros.*;
import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterTrannscodeStreamDriver extends
		BroadcasterLoggableApiWorker implements TestDriver {

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String type, String id, String matrix,
			String max_outputs, String mcast_out, String time_shift,
			String old, String fast_connect, String kompression,
			String enc_type, String enc_key, String rec_history,
			String rec_duration, String src, String ap, String bit, String profile_name) {
		int pid = -8;
		testParameters = new TestParameters("userName:" + userName, "userPass:"
				+ userPass, "login_ip:" + login_ip, "uiport:" + uiport, "type:"
				+ type, "id:" + id, "matrix:" + matrix, "max_outputs:"
				+ max_outputs, "mcast_out:" + mcast_out, "time_shift:"
				+ time_shift, "old:" + old, "fast-connect:" + fast_connect,
				"kompression:" + kompression, "enc-type:" + enc_type,
				"enc-key:" + enc_key, "rec_history:" + rec_history,
				"rec_duration:" + rec_duration, "src:" + src, "vp:" + "   ", "ap:"
						+ ap, "bit:" + bit);

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);
		
		String outJson = apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/zixi/h264_profiles.json" , "", 77, responseCookieContainer, login_ip, this, uiport);

		JSONObject responseJson = new JSONObject(outJson.toString());
		JSONArray outputStreamsArray = responseJson.getJSONArray("profiles");

		String streamName = null;
		for (int i = 0; i < outputStreamsArray.length(); i++) {
			//System.out.println("before");
			JSONObject outputStream = outputStreamsArray.getJSONObject(i);
		   
		    //System.out.println("after");
		    //id1 = stream.getString("id");
		    streamName = outputStream.getString("profile_name");
		    if(streamName.equals(profile_name))
		    {
		    	pid = outputStream.getInt("id");
		    	//testID = profileID;
		    }
		  }
		
		return apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/zixi/add_stream.json?" + rtype + type + "&" + rid + id
				+ "&" + rmatrix + matrix + "&" + rmax_outputs + max_outputs
				+ "&" + rmcast_out + mcast_out + "&" + rtime_shift + time_shift
				+ "&" + rold + old + "&" + rfast_connect + fast_connect + "&"
				+ rkompression + kompression + "&" + renc_type + enc_type
				+ "&" + renc_key + enc_key + "&" + rrec_history + rec_history
				+ "&" + rrec_duration + rec_duration + "&" + rsrc + src + "&"
				+ rvp + pid + "&" + rap + ap + "&" + rbit + bit
		, "", ADD_TRANSCODER_PROFILE, responseCookieContainer, login_ip, this, uiport);
	}

	static private final String rtype = "type=";
	static private final String rid = "id=";
	static private final String rmatrix = "matrix=";
	static private final String rmax_outputs = "max_outputs=";
	static private final String rmcast_out = "mcast_out=";
	static private final String rtime_shift = "time_shift=";
	static private final String rold = "old=";
	static private final String rfast_connect = "fast-connect=";
	static private final String rkompression = "kompression=";
	static private final String renc_type = "enc-type=";
	static private final String renc_key = "enc-key=";
	static private final String rrec_history = "rec_history=";
	static private final String rrec_duration = "rec_duration=";
	static private final String rsrc = "src=";
	static private final String rvp = "vp=";
	static private final String rap = "ap=";
	static private final String rbit = "bit=";
}
