package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;


// This driver helps to create a BX pull output source
public class BroadcasterPullOutputDriver  extends
BroadcasterLoggableApiWorker implements TestDriver {

	
	public String testIMPL(String userName, String userPass, String login_ip, String uiport, 
			String type, String name, String stream, String matrix, String alt_stream, String 
			remote_id, String session, String latency, String session_auth, String stats_hist) 
	{
		testParameters = new TestParameters( "userName:" + userName,  "userPass:"+ userPass, "login_ip:"+login_ip,  "uiport:"+uiport, 
				 "type:"+ type,  "name:"+ name,  "stream:"+ stream,  "matrix:"+matrix,  "alt_stream:"+ alt_stream,  
				"remote_id:"+ remote_id,  "session:"+ session,  "latenc:"+latency,  "session_auth:"+ session_auth,  "stats_hist:"+ stats_hist);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://"
				+ login_ip + ":" + uiport + "/login.htm", userName, userPass,
				login_ip, uiport);

		return apiworker.sendGet("http://"+ login_ip +":"+ uiport + "/zixi/add_output.json?type=" + type + "&"
		+ "name="+ name + "&stream="+ stream + "&matrix=" + matrix +"&alt_stream=" + alt_stream + "&remote_id=" + remote_id +"&"
		+ "session=" +session+ "&latency="+ latency +"&session_auth=" + session_auth + "&stats_hist=" + stats_hist, "", PUSHOUTMODE,
				responseCookieContainer, login_ip, this, uiport);
		// TODO Auto-generated method stub
	}
}
