package com.zixi.drivers;

import static com.zixi.globals.Macros.PUSHINMODE;

import java.net.URLEncoder;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class FeederOutputDeletionDriver extends BroadcasterLoggableApiWorker implements TestDriver {

	private ApiWorkir streamCreator = new ApiWorkir();

	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params7 = "/del_sink?";

	final private static String ruiport = "uiport";
	final private static String rid = "id";
	final private static String rmip = "mip";
	final private static String rport = "port";
	final private static String rip = "ip";
	final private static String rprog = "prog";
	final private static String rchan = "chan";
	final private static String rtype = "type";

	public String testIMPL(String userName, String userPass, String login_ip,
		String uiport, String id, String mip, String port, String ip,
		String prog, String chan, String type, String host) throws Exception {
		
		testParameters = new TestParameters("userName:" + userName, "userPass:"
		+ userPass, "login_ip:" + login_ip, "uiport:" + uiport, "id:"
		+ id, "mip:" + mip, "port:" + port, "ip:" + ip, "prog:" + prog,
		"chan:" + chan, "type:" + type, "host:" + host);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(HTTP
		+ login_ip + ":" + uiport + "/login.htm", userName, userPass,
		login_ip, uiport);
 
		return streamCreator.sendGet(HTTP + login_ip + ":" + uiport
		+ params7 + rmip + "=" + mip + "&" + rport + "=" + port + "&"
		+ rip + "=" + ip + "&" + rprog + "=" + prog + "&" + rchan + "="
		+ chan + "&" + rtype + "=" + type + "&" + rid + "="
		+ "zixi%3A%2F%2F" + host + "%3A2088%2F" + id, id, PUSHINMODE,
		responseCookieContainer, login_ip, this, uiport);
		// TODO Auto-generated method stub
	}
	
	public String testIMPL(String userName, String userPass, String login_ip, String uiport, String id, String mip, 
		String port, String ip, String prog, String chan, String type, String nic1, String nic2, String dest_host1,
		String dest_host2) throws Exception {
		
		testParameters = new TestParameters("userName:"+userName, "userPass:" + userPass, "login_ip:" + login_ip, "uiport:" + uiport, "id:" + id, "mip:" + mip, 
		"port:" + port, "ip:" + ip, "prog:" + prog, "chan:" + chan, "type:" + type, "nic1:" + nic1, "nic2:" + nic2, "dest_host1:" + dest_host1, 
		"dest_host2:" + dest_host2);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(HTTP
		+ login_ip + ":" + uiport + "/login.htm", userName, userPass,
		login_ip, uiport);
 
		String request = "http://" + login_ip + ":" + uiport + "/del_sink?mip=" + mip + "&port=" + port + "&ip=" + ip + "&prog=" + prog + "&chan=" + chan + 
		"&type=" + type + "&id=zixi%3A%2F%2Fbonded(" + id + ")%23" + dest_host1 + "%3A2088%40" + nic1 + "%2C" + dest_host2 + "%3A2088%40" + nic2;
		
		return streamCreator.sendGet(request, id, PUSHINMODE,
		responseCookieContainer, login_ip, this, uiport);
		// TODO Auto-generated method stub
		}
	
	public String testUdpIMPL(String userName, String userPass, String login_ip,
		String uiport, String id, String mip, String port, String ip,
		String prog, String chan, String type, String host, String op) throws Exception 
	{
		testParameters = new TestParameters("userName:" + userName, "userPass:"
		+ userPass, "login_ip:" + login_ip, "uiport:" + uiport, "id:"
		+ id, "mip:" + mip, "port:" + port, "ip:" + ip, "prog:" + prog,
		"chan:" + chan, "type:" + type, "host:" + host);
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(HTTP
		+ login_ip + ":" + uiport + "/login.htm", userName, userPass,
		login_ip, uiport);

		return streamCreator.sendGet(HTTP + login_ip + params1 + uiport
		+ params7 + rmip + "=" + mip + "&" + rport + "=" + port + "&"
		+ rip + "=" + ip + "&" + rprog + "=" + prog + "&" + rchan + "="
		+ chan + "&" + rtype + "=" + type + "&" + rid + "="
		+ "udp%3A%2F%2F" + host + "%3A" + op, id, PUSHINMODE,
		responseCookieContainer, login_ip, this, uiport);
		// TODO Auto-generated method stub
	}
	
	// Delete bonded stream with three links.
	public String testIMPL(String userName, String userPass, String login_ip, String uiport, String id, String mip, 
		String port, String ip, String prog, String chan, String type, String nic1, String nic2, String nic3, String dest_host1,
		String dest_host2, String dest_host3) throws Exception {
		
		testParameters = new TestParameters("userName:"+userName, "userPass:" + userPass, "login_ip:" + login_ip, "uiport:" + uiport, "id:" + id, "mip:" + mip, 
		"port:" + port, "ip:" + ip, "prog:" + prog, "chan:" + chan, "type:" + type, "nic1:" + nic1, "nic2:" + nic2, "dest_host1:" + dest_host1, 
		"dest_host2:" + dest_host2);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(HTTP
		+ login_ip + ":" + uiport + "/login.htm", userName, userPass,
		login_ip, uiport);
 
		String request = "http://" + login_ip + ":" + uiport + "/del_sink?mip=" + mip + "&port=" + port + "&ip=" + ip + "&prog=" + prog + "&chan=" + chan + 
		"&type=" + type + "&id=zixi%3A%2F%2Fbonded(" + id + ")%23" + dest_host1 + "%3A2088%40" + nic1 + "%2C" + dest_host2 + "%3A2088%40" + nic2 + "%2C" +
		dest_host3 + "%3A2088%40" + nic3;
		
		return streamCreator.sendGet(request, id, PUSHINMODE,
		responseCookieContainer, login_ip, this, uiport);
		// TODO Auto-generated method stub
		}
	
	    // Delete bonded stream with three links multiple.
		public String testIMPL(String userName, String userPass, String login_ip, String uiport, String mip, String port, 
		String ip, String prog, String chan, String type, String id) throws Exception {
			
			testParameters = new TestParameters("userName:"+userName, "userPass:" + userPass, "login_ip:" + login_ip, "uiport:" + uiport, "id:" + id);
			
			responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(HTTP + login_ip + ":" + uiport + "/login.htm", userName, userPass, login_ip, uiport);
	 
			String request = "http://" + login_ip + ":" + uiport + "/del_sink?mip=" + mip + "&port=" + port + "&ip=" + ip + "&prog=" + prog + "&chan=" + chan + 
			"&type=" + type + "&id=" +  URLEncoder.encode(id, "UTF-8");
			
			return streamCreator.sendGet(request, id, PUSHINMODE, responseCookieContainer, login_ip, this, uiport);
			// TODO Auto-generated method stub
			}
}
