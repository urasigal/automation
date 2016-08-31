package com.zixi.drivers;

import static com.zixi.globals.Macros.PUSHINMODE;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.text.SimpleAttributeSet;

import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class FeederOutputPushToBxDriver extends BroadcasterLoggableApiWorker implements TestDriver {
	
	private ApiWorkir streamOutCreator = new ApiWorkir();
	
	public String testIMPL(String userName, String userPass, String login_ip,
		String name, String mip, String port, String ip, String prog, String chan, String type, String ostr, String oses, String oetp,
		String oeky, String obit, String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive,
		String ofec, String ofecl, String stop_on_drop, String mmt, String smoothing, String limited, String minbps,
		String lim_enc_addr, String pad_to_cbr, String rtmp_feedback, String ohst, String oprt, String onic, String oalt, String bonded, String uiport) throws Exception {

		testParameters = new TestParameters("userName:" + userName, "userPass:" + userPass, "login_ip:" + login_ip, "name:" + name, "mip:" + mip,
		"port:" + port, "ip:" + ip, "prog:" + prog, "chan:" + chan, "type:" + type, "ostr:" + ostr, "oses:" + oses, "oetp:" + oetp,
		"oeky:" + oeky, "obit:" + obit, "olat:" + olat, "ofc:" + ofc, "ocmp:" + ocmp, "" + oold, "" + onfec, "" + fec_force, "" + fec_adaptive,
		"ofec:" + ofec, "ofecl:" + ofecl, "stop_on_drop:" + stop_on_drop, "mmt:" + mmt, "smoothing:" + smoothing, "limited:" + limited,
		"minbps:" + minbps, "lim_enc_addr:" + lim_enc_addr, "pad_to_cbr:" + pad_to_cbr, "rtmp_feedback" + rtmp_feedback, "ohst:"
		+ ohst, "oprt:" + oprt, "onic:" + onic, "oalt:" + oalt);

		// Get authentication parameters.
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName, userPass, login_ip, uiport);

		String request = "http://" + login_ip + ":" + uiport + "/set_zixi_out?" + "name" + "=" + name + "&" + "mip" + "=" + mip + "&"
		+ "port" + "=" + port + "&" + "ip" + "=" + ip + "&" + "prog" + "=" + prog + "&" + "chan" + "=" + chan + "&" + "type" + "=" + type
		+ "&" + "ostr" + "=" + ostr + "&" + "oses" + "=" + oses + "&" + "oetp" + "=" + oetp + "&" + "oeky" + "=" + oeky + "&" + "obit"
		+ "=" + obit + "&" + "olat" + "=" + olat + "&" + "ofc" + "=" + ofc + "&" + "ocmp" + "=" + ocmp + "&" + "oold" + "=" + oold
		+ "&" + "onfec" + "=" + onfec + "&" + "fec_force" + "=" + fec_force + "&" + "fec_adaptive" + "=" + fec_adaptive + "&"
		+ "ofec" + "=" + ofec + "&" + "ofecl" + "=" + ofecl + "&" + "stop_on_drop" + "=" + stop_on_drop + "&" + "mmt" + "=" + mmt
		+ "&" + "smoothing" + "=" + smoothing + "&" + "limited" + "=" + limited + "&" + "minbps" + "=" + minbps + "&" + "lim_enc_addr"
		+ "=" + lim_enc_addr + "&" + "pad_to_cbr" + "=" + pad_to_cbr+ "&" + "rtmp_feedback" + "=" + rtmp_feedback + "&" + "ohst"
		+ "=" + ohst + "&" + "oprt" + "=" + oprt + "&" + "onic" + "=" + onic + "&" + "oalt" + "=" + oalt +"&"+ "bonded" +"="+bonded;
			
		return streamOutCreator.sendGet(request, name, PUSHINMODE, responseCookieContainer, login_ip, this, uiport);
	}

	public String testIMPL(String userName, String userPass, String login_ip,
		String name, String mip, String port, String ip, String prog, String chan, String type, String ostr, String oses, String oetp,
		String oeky, String obit, String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive,
		String ofec, String ofecl, String stop_on_drop, String mmt, String smoothing, String limited, String minbps,
		String lim_enc_addr, String pad_to_cbr, String rtmp_feedback, String ohst, String oprt, String onic, String oalt, String bonded,
		String rtmp_stream, String rtmp_url, String rtmp_user, String rtmp_pass, String rtmp_url2, String rtmp_hot, String uiport) throws Exception {
		
		testParameters = new TestParameters("userName"+userName,  "userPass"+userPass,  "login_ip"+login_ip,
		"name"+name,  "mip"+mip,  "port"+port,  "ip"+ip,  "prog"+prog, "chan"+chan,  "type"+type,  "ostr"+ostr,  "oses"+oses,  "oetp"+oetp,
		"oeky"+oeky,  "obit"+obit,  "olat"+olat,  "ofc"+ofc, "ocmp"+ocmp, "oold"+oold,  "onfec"+onfec,  "fec_force"+fec_force,  "fec_adaptive"+fec_adaptive,
		"ofec"+ofec,  "ofecl"+ofecl,  "stop_on_drop"+stop_on_drop, "mmt"+ mmt, "smoothing" +smoothing,  ""+limited,  ""+minbps,
		"lim_enc_addr"+lim_enc_addr, "pad_to_cbr" +pad_to_cbr,  "rtmp_feedback"+rtmp_feedback,
		"ohst"+ohst,  "oprt"+oprt,  "onic"+onic,  "oalt"+oalt,  "bonded"+bonded, "rtmp_stream"+rtmp_stream,  "rtmp_url"+rtmp_url, "rtmp_user" +rtmp_user,
		"rtmp_pass"+rtmp_pass,  "rtmp_url2" + rtmp_url2,  "rtmp_hot" + rtmp_hot,  "uiport" +uiport);
		
		// Get authentication parameters.
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet( "http://" + login_ip + ":" + uiport + "/login.htm", userName, userPass, login_ip, uiport);

		String request = "http://" + login_ip + ":" + uiport
		+ "/set_zixi_out?" + "name" + "=" + name + "&" + "mip" + "=" + mip + "&" + "port" + "=" + port + "&" + "ip" + "=" + ip + "&" + "prog" + "="
		+ prog + "&" + "chan" + "=" + chan + "&" + "type" + "=" + type + "&" + "ostr" + "=" + ostr + "&" + "oses" + "=" + oses + "&"
		+ "oetp" + "=" + oetp + "&" + "oeky" + "=" + oeky + "&" + "obit" + "=" + obit + "&" + "olat" + "=" + olat + "&" + "ofc" + "="
		+ ofc + "&" + "ocmp" + "=" + ocmp + "&" + "oold" + "=" + oold + "&" + "onfec" + "=" + onfec + "&" + "fec_force" + "="
		+ fec_force + "&" + "fec_adaptive" + "=" + fec_adaptive + "&" + "ofec" + "=" + ofec + "&" + "ofecl" + "=" + ofecl + "&"
		+ "stop_on_drop" + "=" + stop_on_drop + "&" + "mmt" + "=" + mmt + "&" + "smoothing" + "=" + smoothing + "&" + "limited" + "="
		+ limited + "&" + "minbps" + "=" + minbps + "&" + "lim_enc_addr" + "=" + lim_enc_addr + "&" + "pad_to_cbr" + "=" + pad_to_cbr
		+ "&" + "rtmp_feedback" + "=" + rtmp_feedback + "&" + "ohst" + "=" + ohst + "&" + "oprt" + "=" + oprt + "&" + "onic" + "="
		+ onic + "&" + "oalt" + "=" + oalt +"&"+ "bonded" +"="+bonded + "&rtmp_stream=" + rtmp_stream +  "&rtmp_url=" + rtmp_url + "&rtmp_user=" + rtmp_user +
		"&rtmp_pass=" + rtmp_pass + "&rtmp_url2=" + rtmp_url2 +  "&rtmp_hot=" + rtmp_hot;
			
		return streamOutCreator.sendGet(request, name, PUSHINMODE, responseCookieContainer, login_ip, this, uiport);
	}
	
	public String testIMPL(String userName, String userPass, String login_ip,
		String name, String mip, String port, String ip, String prog, String chan, String type, String ostr, String oses, String oetp,
		String oeky, String obit, String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive,
		String ofec, String ofecl, String stop_on_drop, String mmt, String smoothing, String limited, String minbps,
		String lim_enc_addr, String pad_to_cbr, String rtmp_feedback, 
		String group, String bonded, String bond_host1, String bond_port1, String bond_nic1, String bond_limit1, String bond_backup1, 
		String bond_host2, String bond_port2, String bond_nic2, String bond_limit2, String bond_backup2 ,String uiport) throws Exception {
	
		testParameters = new TestParameters("userName: " + userName,  "userPass:" +userPass,  "login_ip :"+login_ip,
		"name:" +name,  "mip:" + mip,  "port:" + port,  "ip:" + ip,  "prog:" + prog,  "chan:" + chan, "type:" + type,  "ostr:" + ostr, "oses:" + oses, "oetp:" + oetp,
		"oeky:" + oeky,  "obit:" + obit, "olat:" + olat,  "ofc:" + ofc,  "ocmp:" + ocmp, "oold:" + oold, "onfec:" + onfec, "fec_force:" + fec_force, "fec_adaptive:" + fec_adaptive,
		"ofec:" + ofec,  "ofecl:" + ofecl, "stop_on_drop:" + stop_on_drop, "mmt:" + mmt,  "smoothing:" + smoothing, "limited:" + limited,  "minbps:" + minbps,
		"lim_enc_addr:" + lim_enc_addr, "pad_to_cbr:" + pad_to_cbr,  "rtmp_feedback:" + rtmp_feedback, 
		"group:" + group,  "bonded:" + bonded, "bond_host1:" + bond_host1,  "bond_port1:" + bond_port1, "bond_nic1:" + bond_nic1,  "bond_limit1:" + bond_limit1,  "bond_backup1:" + bond_backup1, 
		"bond_host2:" + bond_host2, "bond_port2:" + bond_port2,  "bond_nic2:" + bond_nic2, "bond_limit2:" + bond_limit2,  "bond_backup2:" + bond_backup2 ,"uiport:" + uiport);
	
		// Get authentication parameters.
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName, userPass, login_ip, uiport);
	
		// Format a request string.
		String request = "http://" + login_ip + ":" + uiport + "/set_zixi_out?" + "name" + "=" + name + "&" + "mip" + "=" + mip + "&"
		+ "port" + "=" + port + "&" + "ip" + "=" + ip + "&" + "prog" + "=" + prog + "&" + "chan" + "=" + chan + "&" + "type" + "=" + type
		+ "&" + "ostr" + "=" + ostr + "&" + "oses" + "=" + oses + "&" + "oetp" + "=" + oetp + "&" + "oeky" + "=" + oeky + "&" + "obit"
		+ "=" + obit + "&" + "olat" + "=" + olat + "&" + "ofc" + "=" + ofc + "&" + "ocmp" + "=" + ocmp + "&" + "oold" + "=" + oold
		+ "&" + "onfec" + "=" + onfec + "&" + "fec_force" + "=" + fec_force + "&" + "fec_adaptive" + "=" + fec_adaptive + "&"
		+ "ofec" + "=" + ofec + "&" + "ofecl" + "=" + ofecl + "&" + "stop_on_drop" + "=" + stop_on_drop + "&" + "mmt" + "=" + mmt
		+ "&" + "smoothing" + "=" + smoothing + "&" + "limited" + "=" + limited + "&" + "minbps" + "=" + minbps + "&" + "lim_enc_addr"
		+ "=" + lim_enc_addr + "&" + "pad_to_cbr" + "=" + pad_to_cbr+ "&" + "rtmp_feedback" + "=" + rtmp_feedback + "&group=" + group + "&bonded=" + bonded + "&bond-host=" + bond_host1 +
		"&bond-port=" + bond_port1 + "&bond-nic=" + bond_nic1 + "&bond-limit=" + bond_limit1 + "&bond-backup=" + bond_backup1 +
		"&bond-host=" + bond_host2 + "&bond-port=" + bond_port2 + "&bond-nic=" + bond_nic2 + "&bond-limit=" + bond_limit2 + "&bond-backup=" + bond_backup2;
		
		// Send a request.
		return streamOutCreator.sendGet(request, name, PUSHINMODE, responseCookieContainer, login_ip, this, uiport);
	}
	
	// Bonded stuff.
	public String testIMPL(String userName, String userPass, String login_ip,
		String name, String mip, String port, String ip, String prog, String chan, String type, String ostr, String oses, String oetp,
		String oeky, String obit, String olat, String ofc, String ocmp, String oold, String onfec, String fec_force, String fec_adaptive,
		String ofec, String ofecl, String stop_on_drop, String mmt, String smoothing, String limited, String minbps,
		String lim_enc_addr, String pad_to_cbr, String rtmp_feedback, 
		String group, String bonded, String bond_host1, String bond_port1, String bond_nic1, String bond_limit1, String bond_backup1, 
		String bond_host2, String bond_port2, String bond_nic2, String bond_limit2, String bond_backup2, String bond_host3, String bond_port3, 
		String bond_nic3, String bond_limit3, String bond_backup3, String uiport) throws Exception {
	
		testParameters = new TestParameters("userName: " + userName,  "userPass:" +userPass,  "login_ip :"+login_ip,
		"name:" +name,  "mip:" + mip,  "port:" + port,  "ip:" + ip,  "prog:" + prog,  "chan:" + chan, "type:" + type,  "ostr:" + ostr, "oses:" + oses, "oetp:" + oetp,
		"oeky:" + oeky,  "obit:" + obit, "olat:" + olat,  "ofc:" + ofc,  "ocmp:" + ocmp, "oold:" + oold, "onfec:" + onfec, "fec_force:" + fec_force, "fec_adaptive:" + fec_adaptive,
		"ofec:" + ofec,  "ofecl:" + ofecl, "stop_on_drop:" + stop_on_drop, "mmt:" + mmt,  "smoothing:" + smoothing, "limited:" + limited,  "minbps:" + minbps,
		"lim_enc_addr:" + lim_enc_addr, "pad_to_cbr:" + pad_to_cbr,  "rtmp_feedback:" + rtmp_feedback, 
		"group:" + group,  "bonded:" + bonded, "bond_host1:" + bond_host1,  "bond_port1:" + bond_port1, "bond_nic1:" + bond_nic1,  "bond_limit1:" + bond_limit1,  "bond_backup1:" + bond_backup1, 
		"bond_host2:" + bond_host2, "bond_port2:" + bond_port2,  "bond_nic2:" + bond_nic2, "bond_limit2:" + bond_limit2,  "bond_backup2:" + bond_backup2, "bond_host3:" + bond_host3, 
		"bond_port3:" + bond_port3,  "bond_nic3:" + bond_nic3, "bond_limit3:" + bond_limit3,  "bond_backup3:" + bond_backup3, "uiport:" + uiport);
	
		// Get authentication parameters.
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + login_ip + ":" + uiport + "/login.htm", userName, userPass, login_ip, uiport);
	
		// Format a request string.
		String request = "http://" + login_ip + ":" + uiport + "/set_zixi_out?" + "name" + "=" + name + "&" + "mip" + "=" + mip + "&"
		+ "port" + "=" + port + "&" + "ip" + "=" + ip + "&" + "prog" + "=" + prog + "&" + "chan" + "=" + chan + "&" + "type" + "=" + type
		+ "&" + "ostr" + "=" + ostr + "&" + "oses" + "=" + oses + "&" + "oetp" + "=" + oetp + "&" + "oeky" + "=" + oeky + "&" + "obit"
		+ "=" + obit + "&" + "olat" + "=" + olat + "&" + "ofc" + "=" + ofc + "&" + "ocmp" + "=" + ocmp + "&" + "oold" + "=" + oold
		+ "&" + "onfec" + "=" + onfec + "&" + "fec_force" + "=" + fec_force + "&" + "fec_adaptive" + "=" + fec_adaptive + "&"
		+ "ofec" + "=" + ofec + "&" + "ofecl" + "=" + ofecl + "&" + "stop_on_drop" + "=" + stop_on_drop + "&" + "mmt" + "=" + mmt
		+ "&" + "smoothing" + "=" + smoothing + "&" + "limited" + "=" + limited + "&" + "minbps" + "=" + minbps + "&" + "lim_enc_addr"
		+ "=" + lim_enc_addr + "&" + "pad_to_cbr" + "=" + pad_to_cbr+ "&" + "rtmp_feedback" + "=" + rtmp_feedback + "&group=" + group + "&bonded=" + bonded + "&bond-host=" + bond_host1 +
		"&bond-port=" + bond_port1 + "&bond-nic=" + bond_nic1 + "&bond-limit=" + bond_limit1 + "&bond-backup=" + bond_backup1 +
		"&bond-host=" + bond_host2 + "&bond-port=" + bond_port2 + "&bond-nic=" + bond_nic2 + "&bond-limit=" + bond_limit2 + "&bond-backup=" + bond_backup2 + 
		"&bond-host=" + bond_host3 + "&bond-port=" + bond_port3 + "&bond-nic=" + bond_nic3 + "&bond-limit=" + bond_limit3 + "&bond-backup=" + bond_backup3;
		
		// Send a request.
		return streamOutCreator.sendGet(request, name, PUSHINMODE, responseCookieContainer, login_ip, this, uiport);
		}
}
