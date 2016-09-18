package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterPushOutStreamCreationDriver extends BroadcasterLoggableApiWorker implements TestDriver {
	
	final private static String params5 = "ie_fooler=0.45086039789021015";

	public String testIMPL(String userName, String userPass, String login_ip, String host, String latency, String fec_force, String session,
	String fec_adaptive, String nic, String fec_block, String type, String snames, String fec_aware, String fec_overhead,
	String stream, String port, String uiport, String alias, String id) throws Exception {
		
		testParameters = new TestParameters("userName:"+ userName ,"userPass:"+ userPass ,"login_ip:"+ login_ip,"host:"+ host ,"latency:"+ latency ,
		"fec_force:"+ fec_force ,"session:"+ session,"fec_adaptive:"+ fec_adaptive ,"nic:"+ nic ,"fec_block:"+ fec_block ,"type:"+ type,
		"snames:"+ snames ,"fec_aware:"+ fec_aware,"fec_overhead:"+ fec_overhead ,"stream:"+ stream,"port:"+ port,"uiport:"+ uiport ,"alias:"+ alias ,"id:"+ id);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName , userPass, login_ip, uiport);
		
		return apiworker.sendGet("http://" + login_ip + ":" + uiport + "/zixi/add_output.json?" + "type" + "=" + type + "&" + "id" + "="
		+ id + "&" + "name" + "=" + snames + "&" + "stream" + "=" + stream + "&" + "alias" + "=" + alias
		+ "&" + "session" + "=" + session + "&" + "host0" + "=" + host + "&" + "port" + "=" + port + "&"
		+ "latency" + "=" + latency + "&" + "nic" + "=" + nic+ "&" + "fec_force" + "=" + fec_force + "&"
		+ "fec_overhead" + "=" + fec_overhead + "&" + "fec_block" + "=" + fec_block + "&" + "fec_adaptive" + "="
		+ fec_adaptive + "&" + "fec_aware" + "=" + fec_aware + "&" + params5, id, PUSHOUTMODE, responseCookieContainer, login_ip, this, uiport);
	}

	public String testIMPL(String userName_src, String userPass_src, String login_ip_src, String uiport_src, String type_src, String name, 
	String stream, String matrix, String alias, String session, String link_a, String link_b, String bond_links, String latency_src, 
	String fec_force, String fec_overhead, String fec_block, String fec_adaptive, String mmt, String fec_aware, String stats_hist) throws Exception {
		
		testParameters = new TestParameters("userName:"+ userName_src ,"userPass:"+ userPass_src ,"login_ip:"+ login_ip_src, "ui port:" + uiport_src,
		"type:" + type_src, "name:" + name, "stream:"+ stream, "matrix:" + matrix, "alias:" + alias, "session:" + session, "link:" + link_a,
		"link:" + link_b, "bond_links:" + bond_links, "latency:" + latency_src, "fec_force:" + fec_force, "fec_overhead:" + fec_overhead, "fec_block:" + fec_block,
		"fec_adaptive:" + fec_adaptive, "mmt:" + mmt, "fec_aware:" + fec_aware, "stats_hist:" + stats_hist);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip_src + ":" + uiport_src + "/login.htm", userName_src , 
		userPass_src, login_ip_src, uiport_src);
		
		return apiworker.sendGet("http://" + login_ip_src +  ":" + uiport_src + "/zixi/add_output.json?type=" + type_src + "&name=" + name +"&stream=" + stream + 
		"&matrix=" + matrix + "&alias=" + alias + "&session=" + session + "&link=" + link_a + "&link=" + link_b +  "&bond_links=" + bond_links + "&latency=" + latency_src +
		"&fec_force=" + fec_force +"&fec_overhead=" + fec_overhead + "&fec_block=" + fec_block + "&fec_adaptive=" + fec_adaptive  + "&mmt=" + mmt + 
		"&fec_aware=" + fec_aware + "&stats_hist=" + stats_hist, "", PUSHOUTMODE, responseCookieContainer, login_ip_src, this, uiport_src);
	}
}
