package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class ReceiverPullInputStreamCreationDriver extends BroadcasterLoggableApiWorker
		implements TestDriver {

	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params5 = "ie_fooler=0.45086039789021015";
	final private static String params6 = "&smoothing=";
	final private static String params7 = "/add_zixi_in.json?";

	final private static String rdec_key = "dec-key";
	final private static String rdec_type = "dec-type";
	final private static String rfec_adaptive = "fec_adaptive";
	final private static String rfec_aware = "fec_aware";
	final private static String rfec_force = "fec_force";
	final private static String rfec_latency = "fec_latency";
	final private static String rfec_overhead = "fec_overhead";
	final private static String rhost = "host";
	final private static String rlatency = "latency";
	final private static String rmin_bit = "min_bit";
	final private static String rname = "name";
	final private static String rnic = "nic";
	final private static String rport = "port";
	final private static String rsession = "session";
	final private static String rstream = "stream";

	protected ApiWorkir streamCreator = new ApiWorkir();

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String dec_key, String dec_type,
			String fec_adaptive, String fec_aware, String fec_force,
			String fec_latency, String fec_overhead, String host,
			String latency, String min_bit, String name, String nic,
			String port, String session, String stream) throws Exception {
		
		testParameters = new TestParameters("userName:"+userName, "userPass:"+userPass, "login_ip:"+login_ip,
				"uiport:"+uiport, "dec_key:"+dec_key, "dec_type:"+dec_type, "fec_adaptive:"+fec_adaptive, "fec_aware:"+fec_aware, "fec_force:"+fec_force,
				"fec_latency:"+fec_latency, "fec_overhead:"+fec_overhead, "host:"+host, "latency:"+latency, "min_bit:"+min_bit, "name:"+name, "nic:"+nic,
				"port:"+port, "session:"+session, "stream:"+ stream);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		return streamCreator.sendGet(HTTP + login_ip + params1 + uiport
				+ params7 + rdec_key + "=" + dec_key + "&" + rdec_type +"=" + dec_type +"&"+ rfec_adaptive + "="+ fec_adaptive + "&" + rfec_aware +"="+ fec_aware +"&"+
				rfec_force +"="+ fec_force +"&"+ rfec_latency +"="+ fec_latency +"&"+ rfec_overhead +"="+ fec_overhead +"&"+ rhost +"="+ host +"&"+ 
				rlatency +"="+ latency +"&"+ rmin_bit +"="+  min_bit +"&"+ rname +"="+ name +"&"+ rnic +"="+ nic +"&"+ rport +"="+ port +"&"+ rsession +"="+ session +
				"&"+ rstream +"="+ stream , "", RECEIVERMODE,
				responseCookieContainer , login_ip, this, uiport);
	}

}
