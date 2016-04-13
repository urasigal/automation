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

public class FeederOutputPushToBxDriver extends BroadcasterLoggableApiWorker implements
		TestDriver {

	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params5 = "ie_fooler=0.45086039789021015";
	final private static String params7 = "/set_zixi_out?";

	final private static String ruserName = "userName";
	final private static String ruserPass = "userPass";
	final private static String rlogin_ip = "login_ip";
	final private static String rname = "name";
	final private static String rmip = "mip";
	final private static String rport = "port";
	final private static String rip = "ip";
	final private static String rprog = "prog";
	final private static String rchan = "chan";
	final private static String rtype = "type";
	final private static String rostr = "ostr";
	final private static String roses = "oses";
	final private static String roetp = "oetp";
	final private static String roeky = "oeky";
	final private static String robit = "obit";
	final private static String rolat = "olat";
	final private static String rofc = "ofc";
	final private static String rocmp = "ocmp";
	final private static String roold = "oold";
	final private static String ronfec = "onfec";
	final private static String rfec_force = "fec_force";
	final private static String rfec_adaptive = "fec_adaptive";
	final private static String rofec = "ofec";
	final private static String rofecl = "ofecl";
	final private static String rstop_on_drop = "stop_on_drop";
	final private static String rmmt = "mmt";
	final private static String rsmoothing = "smoothing";
	final private static String rlimited = "limited";
	final private static String rminbps = "minbps";
	final private static String rlim_enc_addr = "lim_enc_addr";
	final private static String rpad_to_cbr = "pad_to_cbr";
	final private static String rrtmp_feedback = "rtmp_feedback";
	final private static String rohst = "ohst";
	final private static String roprt = "oprt";
	final private static String ronic = "onic";
	final private static String roalt = "oalt";
	final private static String rbonded = "bonded"; 
	private ApiWorkir streamOutCreator = new ApiWorkir();
	
	public String testIMPL(String userName, String userPass, String login_ip,
			String name, String mip, String port, String ip, String prog,
			String chan, String type, String ostr, String oses, String oetp,
			String oeky, String obit, String olat, String ofc, String ocmp,
			String oold, String onfec, String fec_force, String fec_adaptive,
			String ofec, String ofecl, String stop_on_drop, String mmt,
			String smoothing, String limited, String minbps,
			String lim_enc_addr, String pad_to_cbr, String rtmp_feedback,
			String ohst, String oprt, String onic, String oalt, String bonded, String uiport) {

		testParameters = new TestParameters("userName:" + userName, "userPass:"
				+ userPass, "login_ip:" + login_ip, "name:" + name, "mip:" + mip,
				"port:" + port, "ip:" + ip, "prog:" + prog, "chan:" + chan, "type:"
						+ type, "ostr:" + ostr, "oses:" + oses, "oetp:" + oetp,
				"oeky:" + oeky, "obit:" + obit, "olat:" + olat, "ofc:" + ofc, "ocmp:" + ocmp,
				"" + oold, "" + onfec, "" + fec_force, "" + fec_adaptive,
				"ofec:" + ofec, "ofecl:" + ofecl, "stop_on_drop:" + stop_on_drop,
				"mmt:" + mmt, "smoothing:" + smoothing, "limited:" + limited,
				"minbps:" + minbps, "lim_enc_addr:" + lim_enc_addr, "pad_to_cbr:"
						+ pad_to_cbr, "rtmp_feedback" + rtmp_feedback, "ohst:"
						+ ohst, "oprt:" + oprt, "onic:" + onic, "oalt:" + oalt);

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		String request = HTTP + login_ip + params1 + uiport
				+ params7 + rname + "=" + name + "&" + rmip + "=" + mip + "&"
				+ rport + "=" + port + "&" + rip + "=" + ip + "&" + rprog + "="
				+ prog + "&" + rchan + "=" + chan + "&" + rtype + "=" + type
				+ "&" + rostr + "=" + ostr + "&" + roses + "=" + oses + "&"
				+ roetp + "=" + oetp + "&" + roeky + "=" + oeky + "&" + robit
				+ "=" + obit + "&" + rolat + "=" + olat + "&" + rofc + "="
				+ ofc + "&" + rocmp + "=" + ocmp + "&" + roold + "=" + oold
				+ "&" + ronfec + "=" + onfec + "&" + rfec_force + "="
				+ fec_force + "&" + rfec_adaptive + "=" + fec_adaptive + "&"
				+ rofec + "=" + ofec + "&" + rofecl + "=" + ofecl + "&"
				+ rstop_on_drop + "=" + stop_on_drop + "&" + rmmt + "=" + mmt
				+ "&" + rsmoothing + "=" + smoothing + "&" + rlimited + "="
				+ limited + "&" + rminbps + "=" + minbps + "&" + rlim_enc_addr
				+ "=" + lim_enc_addr + "&" + rpad_to_cbr + "=" + pad_to_cbr
				+ "&" + rrtmp_feedback + "=" + rtmp_feedback + "&" + rohst
				+ "=" + ohst + "&" + roprt + "=" + oprt + "&" + ronic + "="
				+ onic + "&" + roalt + "=" + oalt +"&"+ rbonded +"="+bonded;
			
		return streamOutCreator.sendGet(request, name, PUSHINMODE,
				responseCookieContainer, login_ip, this, uiport);
		// TODO Auto-generated method stub
	}
}
