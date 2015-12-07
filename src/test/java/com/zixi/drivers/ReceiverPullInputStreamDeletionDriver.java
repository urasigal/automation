package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class ReceiverPullInputStreamDeletionDriver extends BroadcasterLoggableApiWorker
implements TestDriver{

	protected ApiWorkir streamCreator = new ApiWorkir();
	
	
	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String id) {
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);
		
		 String streamId =  streamCreator.sendGet("http://"+ login_ip + ":" + uiport + "/in_streams.json?complete=1", id, RECEIVERIDMODE, responseCookieContainer, login_ip, this, uiport);
		 
		 return streamCreator.sendGet("http://" + login_ip + ":" + uiport + "/remove_in.json?id=" + streamId, id, RECEIVERDELETIONMODE, responseCookieContainer, login_ip, this, uiport);
	}
}
 