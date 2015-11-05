package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggable;

public class BroadcasterPushOutStreamCreationDriver extends BroadcasterLoggable
		implements TestDriver {

	private ApiWorkir streamCreator = new ApiWorkir();

	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params5 = "ie_fooler=0.45086039789021015";
	final private static String params7 = "/zixi/add_output.json?";

	final private String rtype = "type";
	final private String rid = "id";
	final private String rname = "name";
	final private String rstream = "stream";
	final private String ralias = "alias";
	final private String rsession = "session";
	final private String rhost0 = "host0";
	final private String rport = "port";
	final private String rlatency = "latency";
	final private String rnic = "nic";
	final private String rfec_force = "fec_force";
	final private String rfec_overhead = "fec_overhead";
	final private String rfec_block = "fec_block";
	final private String rfec_adaptive = "fec_adaptive";
	final private String rfec_aware = "fec_aware";

	public String testIMPL(String userName, String userPass, String login_ip,
			String host, String latency, String fec_force, String session,
			String fec_adaptive, String nic, String fec_block, String type,
			String snames, String fec_aware, String fec_overhead,
			String stream, String port, String uiport, String alias, String id) {
		
		testParameters = new TestParameters("userName:"+ userName ,"userPass:"+ userPass ,"login_ip:"+ login_ip
				,"host:"+ host ,"latency:"+ latency ,"fec_force:"+ fec_force ,"session:"+ session
				,"fec_adaptive:"+ fec_adaptive ,"nic:"+ nic ,"fec_block:"+ fec_block ,"type:"+ type
				,"snames:"+ snames ,"fec_aware:"+ fec_aware,"fec_overhead:"+ fec_overhead
				,"stream:"+ stream,"port:"+ port,"uiport:"+ uiport ,"alias:"+ alias ,"id:"+ id);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPass, login_ip, uiport);
		return streamCreator.sendGet(HTTP + login_ip + params1 + uiport
				+ params7 + rtype + params4 + type + params2 + rid + params4
				+ id + params2 + rname + params4 + snames + params2 + rstream
				+ params4 + stream + params2 + ralias + params4 + alias
				+ params2 + rsession + params4 + session + params2 + rhost0
				+ params4 + host + params2 + rport + params4 + port + params2
				+ rlatency + params4 + latency + params2 + rnic + params4 + nic
				+ params2 + rfec_force + params4 + fec_force + params2
				+ rfec_overhead + params4 + fec_overhead + params2 + rfec_block
				+ params4 + fec_block + params2 + rfec_adaptive + params4
				+ fec_adaptive + params2 + rfec_aware + params4 + fec_aware
				+ params2 + params5, id, PUSHOUTMODE, responseCookieContainer,
				login_ip, this, uiport);
	}
}
