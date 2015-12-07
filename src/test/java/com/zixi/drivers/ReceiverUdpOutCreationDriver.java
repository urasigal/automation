package com.zixi.drivers;

import static com.zixi.globals.Macros.*;
import static com.zixi.globals.Macros.RECEIVERIDMODE;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class ReceiverUdpOutCreationDriver extends BroadcasterLoggableApiWorker implements
		TestDriver {

	private ApiWorkir streamCreator = new ApiWorkir();

	private TestParameters testParameters;

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String name, String target, String type, String nic,
			String ttl, String smoothing, String rtp, String fec, String rows,
			String cols, String remux_bitrate,String input_stream) 
	{
		
		testParameters = new TestParameters("userName:"+userName, "userPass:"+userPass, "login_ip:"+login_ip,
				"uiport:"+uiport, "name:"+name, "target:"+target, "type:"+type, "nic:"+nic,
				"ttl:"+ttl, "smoothing:"+smoothing, "rtp:"+rtp, "fec:"+fec, "rows:"+rows,
				"cols:"+cols, "remux_bitrate:"+remux_bitrate,"input_stream:"+input_stream);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		String streamId = streamCreator.sendGet("http://" + login_ip + ":"
				+ uiport + "/in_streams.json?complete=1", input_stream, RECEIVERIDMODE,
				responseCookieContainer, login_ip, this, uiport);

		return apiworker.sendGet("http://" + login_ip + ":" + uiport
				+ "/add_output.json?" + rname + "=" + name + "&" + rtarget
				+ "=" + target + "&" + rtype + "=" + type + "&" + rnic + "="
				+ nic + "&" + rttl + "=" + ttl + "&" + rsmoothing + "="
				+ smoothing + "&" + rrtp + "=" + rtp + "&" + rfec + "=" + fec
				+ "&" + rrows + "=" + rows + "&" + rcols + "=" + cols + "&"
				+ rremux_bitrate + "=" + remux_bitrate +"&"+ rin_id +"="+ streamId , "", RECEIVER_UDP_OUT_MODE,
				responseCookieContainer , login_ip, this, uiport);
	}

	final private static String rname = "name";
	final private static String rtarget = "target";
	final private static String rtype = "type";
	final private static String rnic = "nic";
	final private static String rttl = "ttl";
	final private static String rsmoothing = "smoothing";
	final private static String rrtp = "rtp";
	final private static String rfec = "fec";
	final private static String rrows = "rows";
	final private static String rcols = "cols";
	final private static String rremux_bitrate = "remux_bitrate";
	final private static String rin_id = "in_id";

}
