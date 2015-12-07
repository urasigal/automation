package com.zixi.drivers;

import static com.zixi.globals.Macros.PUSHINMODE;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterRtmpPushOutputCreationDriver extends
		BroadcasterLoggableApiWorker implements TestDriver {

	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params7 = "/zixi/add_output.json?";

	public String testIMPL(String login_ip, String userName,
			String userPassword, String uiport, String type, String name,
			String stream, String matrix, String url, String url_alt,
			String rtmp_stream, String user, String bandwidth, String latency,
			String reconnect, String sendfi, String disconnect_low_br,
			String static_latency, String dec_type, String dec_key,
			String password) {

		testParameters = new TestParameters("login_ip:" + login_ip, "userName:"
				+ userName, "userPassword:" + userPassword, "uiport:" + uiport,
				"type:" + type, "stream:" + stream, "matrix:" + matrix, "url:"
						+ url, "url_alt:" + url_alt, "rtmp_stream:"
						+ rtmp_stream, "user:" + user,
				"bandwidth:" + bandwidth, "latency:" + latency, "reconnect:"
						+ reconnect, "sendfi:" + sendfi, "disconnect_low_br:"
						+ disconnect_low_br,
				"static_latency:" + static_latency, "dec_type:" + dec_type,
				"dec_key:" + dec_key, "password:" + password);

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPassword, login_ip, uiport);

		return apiworker.sendGet(
				HTTP + login_ip + params1 + uiport + params7 + "type" + "="
						+ type + "&" + "name" + "=" + name + "&" + "stream"
						+ "=" + stream + "&" + "matrix" + "=" + matrix + "&"
						+ "url" + "=" + url + "&" + "url-alt" + "=" + url_alt
						+ "&" + "rtmp-stream" + "=" + rtmp_stream + "&" + user
						+ "=" + user + "&" + "bandwidth" + "=" + bandwidth
						+ "&" + "latency" + "=" + latency + "&" + reconnect
						+ "=" + reconnect + "&" + "sendfi" + "=" + sendfi + "&"
						+ "disconnect_low_br" + "=" + disconnect_low_br + "&"
						+ "static_latency" + "=" + static_latency + "&"
						+ "dec-type" + "=" + dec_type + "&" + "dec-key" + "="
						+ dec_key + "&" + "password" + "=" + password, "",
				PUSHINMODE, responseCookieContainer, login_ip, this, uiport);
	}
}
