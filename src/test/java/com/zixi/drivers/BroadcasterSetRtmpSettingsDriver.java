package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterSetRtmpSettingsDriver extends BroadcasterLoggableApiWorker implements TestDriver{

	public Object testIMPL(String userName, String userPass, String login_ip, String uiport,
			String rtmp_on, String rtmp_port, String rtmp_auto_out,
			String rtmp_auto_in, String rtmp_pcr_int,
			String rtmp_auto_out_latency) throws Exception {
		
		testParameters = new TestParameters();
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);
		
		  return apiworker.sendGet("http://"+ login_ip + ":" + uiport + "/apply_settings.json?rtmp_on=" + rtmp_on + "&rtmp_port=" + rtmp_port + "&rtmp_auto_out=" + 
				  					1 +"&rtmp_auto_in="+ rtmp_auto_out + "&rtmp_pcr_int=" + rtmp_auto_in + "&rtmp_auto_out_latency="+ rtmp_auto_out_latency, 
			  "", SET_RTMMP_AUTO_REMOTE,
				responseCookieContainer, login_ip, this, uiport);
		
	}

}