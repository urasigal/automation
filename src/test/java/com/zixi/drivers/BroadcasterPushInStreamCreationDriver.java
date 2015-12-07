package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.zixi.entities.StreamEntity;
import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterPushInStreamCreationDriver extends BroadcasterLoggableApiWorker implements TestDriver{
	
	private ApiWorkir streamCreator = new ApiWorkir();
	private TestParameters testParameters;
	
	public String testIMPL(String userName,String userPass,String login_ip,String latency,String time_shift,String force_p2p,String mcast_ip,String mcast_force,String mcast_port,String type,
			String uiport,String analyze,String mcast_ttl,String id,String mcast_out,String complete,String max_outputs,String on, String password)
	{
		testParameters = new TestParameters(userName,userPass,login_ip,latency,
				time_shift,force_p2p,mcast_ip,mcast_force,mcast_port,type,uiport,analyze,mcast_ttl,id,mcast_out,complete,
				max_outputs,on, password);
		
		// Print out to the HTML test report the test's parameters
		testParameters.printParametersToHTML();
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPass, login_ip, uiport);
		return streamCreator.sendGet(HTTP + login_ip + params1
				+ uiport + params7 + rtype
				+ params4 + type + params2 + rpassword + params4 + password
				+ params2 + rlatency + params4 + latency + params2 + rforce_p2p
				+ params4 + force_p2p + params2 + rid + params4 + id 
				+ params2 + rmax_outputs + params4 + max_outputs + params2
				+ rmcast_out + params4 + mcast_out + params2 + ron + params4
				+ on + params2 + ranalyze + params4 + analyze + params2
				+ rcomplete + params4 + complete + params2 + rmcast_force
				+ params4 + mcast_force + params2 + rmcast_ip + params4
				+ mcast_ip + params2 + rmcast_port + params4 + mcast_port
				+ params2 + rmcast_ttl + params4 + mcast_port + params2
				+ rtime_shift + params4 + time_shift + params2 + params5, id, PUSHINMODE, responseCookieContainer, login_ip, this, uiport); 
	}
	
	final protected static String HTTP = "http://";
	final protected static String params1 = ":";
	final protected static String params2 = "&";
	final protected static String params4 = "=";
	final protected static String params5 = "ie_fooler=0.45086039789021015";
	protected static String params7 = "/zixi/add_stream.json?";

	final protected String rfunc = "func";
	final protected String ron = "on";
	final protected String rpassword = "password";
	final protected String rlatency = "latency";
	final protected String rforce_p2p = "force_p2p";
	final protected String rtype = "type";
	final protected String rid = "id";
	final protected String rmax_outputs = "max_outputs";
	final protected String rmcast_out = "mcast_out";
	final protected String rmcast_force = "mcast_force";
	final protected String rmcast_ip = "mcast_ip";
	final protected String rmcast_port = "mcast_port";
	final protected String rmcast_ttl = "mcast_ttl";
	final protected String rtime_shift = "time_shift";
	final protected String rcomplete = "complete";
	final protected String ranalyze = "analyze";
}
