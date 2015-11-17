package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggable;

public class BroadcasterRtmpPushInputStreamDriver extends BroadcasterLoggable
		implements TestDriver {

	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params4 = "=";
	final private static String params7 = "/zixi/add_stream.json?";

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String type, String id, String matrix,
			String max_outputs, String mcast_out, String time_shift,
			String old, String fast_connect, String kompression,
			String enc_type, String enc_key, String rec_history,
			String rec_duration, String rtmp_url, String rtmp_name,
			String rtmp_user) {
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		return apiworker.sendGet(HTTP + login_ip + params1 + uiport + params7
				+ rtype + "=" + type + "&" + rid + "=" + id + "&" + rmatrix
				+ "=" + matrix + "&" + rmax_outputs + "=" + max_outputs
				+ "&" + rmcast_out + "=" + mcast_out + "&" + rtime_shift
				+ "=" + time_shift + "&" + rold + "=" + old + "&"
				+ rfast_connect + "=" + fast_connect + "&" + rkompression
				+ "=" + kompression + "&" + renc_type + "=" + enc_type
				+ "&" + renc_key + "=" + enc_key + "&" + rrec_history + "="
				+ rec_history + "&" + rrec_duration + "=" + rec_duration
				+ "&" + rrtmp_url + "=" + rtmp_url + "&" + rrtmp_name + "="
				+ rtmp_name + "&" + rrtmp_user + "=" + rtmp_user, "",
				PUSHINMODE, responseCookieContainer, login_ip, this, uiport);
	}

	final private static String rtype = "type";
	final private static String rid = "id";
	final private static String rmatrix = "matrix";
	final private static String rmax_outputs = "max_outputs";
	final private static String rmcast_out = "mcast_out";
	final private static String rtime_shift = "time_shift";
	final private static String rold = "old";
	final private static String rfast_connect = "fast-connect";
	final private static String rkompression = "kompression";
	final private static String renc_type = "enc-type";
	final private static String renc_key = "enc-key";
	final private static String rrec_history = "rec_history";
	final private static String rrec_duration = "rec_duration";
	final private static String rrtmp_url = "rtmp_url";
	final private static String rrtmp_name = "rtmp_name";
	final private static String rrtmp_user = "rtmp_user";

}
