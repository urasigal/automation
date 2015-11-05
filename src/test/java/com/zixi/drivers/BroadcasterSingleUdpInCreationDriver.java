package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggable;
import com.zixi.tools.ApiWorkir;

public class BroadcasterSingleUdpInCreationDriver extends BroadcasterLoggable implements TestDriver {

	private ApiWorkir streamCreator = new ApiWorkir();
	
	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params5 = "ie_fooler=0.45086039789021015";
	final private static String params7 = "/zixi/add_stream.json?";

	
	final private String rtime_shift = "time_shift";
	final private String rmax_outputs = "max_outputs";
	final private String rid = "id";
	final private String ron = "on";
	final private String rtype = "type";
	final private String rmcast_out = "mcast_out";
	final private String rmcast_force = "mcast_force";
	final private String rmcast_ip = "mcast_ip";
	final private String rmcast_port = "mcast_port";
	final private String rmcast_ttl = "mcast_ttl";
	final private String rcomplete = "complete";
	final private String rts_port = "ts-port";
	final private String rmax_bitrate = "max_bitrate";
	final private String rmulticast = "multicast";
	final private String rmulti_src = "multi_src";
	final private String rnic = "nic";
	final private String rkompression = "kompression";
	final private String renc_type = "enc-type";
	final private String renc_key = "enc-key";
	final private String rrtp_type = "rtp-type";
	
	public String testIMPL(String userName, String userPass,
			String loin_ip, String ts_port, String id,String rtp_type,String multi_src, String max_bitrate,
			String time_shift,String mcast_ip,String mcast_force, String mcast_port, String nic,String type,String multicast,
			String enc_key, String kompression,
			String uiport,String mcast_ttl,String enc_type,String mcast_out,String complete,String max_outputs,String on) {
		testParameters = new TestParameters("userName:"+userName, "userPass:"+userPass, "loin_ip:"+loin_ip, "ts_port:"+ts_port, "id:"+id,
				"rtp_type:"+rtp_type, "multi_src:"+multi_src, "max_bitrate:"+max_bitrate, "time_shift:"+time_shift, "mcast_ip:"+mcast_ip, 
				"mcast_force:"+mcast_force, "mcast_port:"+mcast_port, "nic:"+nic, "type:"+type, "multicast:"+multicast, "enc_key:"+enc_key,"kompression:"+kompression);
		
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + loin_ip + ":" + uiport + "/login.htm", userName , userPass, loin_ip, uiport);
		
		return streamCreator.sendGet(
				HTTP + loin_ip + params1 + uiport + params7 + rtype
				+ params4 + type + params2 + rid + params4 + id 
				+ params2 + rmax_outputs + params4 + max_outputs
				+ params2 + rmcast_out + params4 + mcast_out + params2
				+ rmcast_force + params4 + mcast_force + params2
				+ rmcast_ip + params4 + mcast_ip + params2
				+ rmcast_port + params4 + mcast_port + params2
				+ rmcast_ttl + params4 + mcast_ttl + params2
				+ rtime_shift + params4 + time_shift + params2
				+ rcomplete + params4 + complete + params2 + ron
				+ params4 + on + params2 + rts_port + params4
				+ ts_port + params2
				+ rmax_bitrate + params4 + max_bitrate + params2
				+ rmulticast + params4 + multicast + params2
				+ rmulti_src + params4 + multi_src + params2 + rnic
				+ params4 + nic + params2 + rkompression + params4
				+ kompression + params2 + renc_type + params4
				+ enc_type + params2 + renc_key + params4 + enc_key
				+ params2 + rrtp_type + params4 + rtp_type ,
				id, UDPMODE, responseCookieContainer, loin_ip, this, uiport);
	}
}
