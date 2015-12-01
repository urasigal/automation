package com.zixi.drivers;

import static com.zixi.globals.Macros.RECEIVER_UDP_OUT_MODE;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggable;

public class BroadcasterCreateAddaptiveGroupDriver extends BroadcasterLoggable
		implements TestDriver {

	private ApiWorkir adaptiveGroupCreator = new ApiWorkir();

	private TestParameters testParameters;

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String name, String record, String zixi, String hls,
			String hds, String mpd, String mmt, String compress_zixi,
			String multicast, String streams, String bitrates, String max_time) {

		testParameters = new TestParameters("userName" + userName, "userPass"
				+ userPass, "login_ip" + login_ip, "uiport" + uiport, "name"
				+ name, "record" + record, "zixi" + zixi, "hls" + hls, "hds"
				+ hds, "mpd" + mpd, "mmt" + mmt, "compress_zixi"
				+ compress_zixi, "multicast" + multicast, "streams" + streams,
				"bitrates" + bitrates, "max_time" + max_time);

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		return apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/zixi/set_adaptive_channel_with_streams.json?" + rname
				+ name + "&" + rrecord + record + "&" + rzixi + zixi + "&"
				+ rhls + hls + "&" + rhds + hds + "&" + rmpd + mpd + "&" + rmmt
				+ mmt + "&" + rcompress_zixi + compress_zixi + "&" + rmulticast
				+ multicast + streamsNames(streams) + streamsBitrates(bitrates)
				+ "&" + rmax_time + max_time, "", 77, responseCookieContainer,
				login_ip, this, uiport);
	}

	private static String streamsNames(String streams) {
		StringBuilder stringBuilder = new StringBuilder();
		String[] streamNames = streams.split(",");
		for (String name : streamNames) {
			stringBuilder.append("&stream=" + name);
		}
		return stringBuilder.toString();
	}

	private static String streamsBitrates(String bitrates) {
		StringBuilder stringBuilder = new StringBuilder();
		String[] streamBits = bitrates.split(",");
		for (String bitrate : streamBits) {
			stringBuilder.append("&bitrate=" + bitrate);
		}
		return stringBuilder.toString();
	}

	final private static String rname = "name=";
	final private static String rrecord = "record=";
	final private static String rzixi = "zixi=";
	final private static String rhls = "hls=";
	final private static String rhds = "hds=";
	final private static String rmpd = "mpd=";
	final private static String rmmt = "mmt=";
	final private static String rcompress_zixi = "compress-zixi=";
	final private static String rmulticast = "multicast=";
	final private static String rmax_time = "max_time=";
}
