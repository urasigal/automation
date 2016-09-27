package com.zixi.drivers;

import static com.zixi.globals.Macros.*;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;
import com.zixi.tools.ApiWorkir;

public class BroadcasterUdpOutputCreationDriver extends BroadcasterLoggableApiWorker implements TestDriver {

	// Default constructor.
	public BroadcasterUdpOutputCreationDriver() {}
	
	public BroadcasterUdpOutputCreationDriver(StringBuffer 	testFlowDescriptor) {
		super(testFlowDescriptor);
	}
	
	public String testIMPL(String userName, String userPass, String loin_ip, String port, String stream, String streamname, String host,
	String id, String rtp, String fec, String smoothing, String ttl, String remux_bitrate, String df, String local_port, String dec_key,
	String type, String rows, String remux_buff, String local_ip, String remux_restampdts, String uiport, String remux_pcr, String dec_type, String cols) throws Exception {
		
		testFlowDescriptor.append("\nUsing BroadcasterUdpOutputCreationDriver driver");
		
		testParameters = new TestParameters("userName:"+ userName, "userPass:"+ userPass, "loin_ip:"+ loin_ip,
		"port:"+ port, "stream:"+ stream, "streamname:"+ streamname, "host:"+ host,"id:"+ id, "rtp:"+ rtp, "fec:"+ fec, "smoothing:"+ smoothing, "ttl:"+ ttl,
		"remux_bitrate:"+ remux_bitrate, "df:"+ df, "local_port:"+ local_port, "dec_key:"+ dec_key,"type:"+ type, "rows:"+ rows, "remux_buff:"+ remux_buff, "local_ip:"+ local_ip,
		"remux_restampdts:"+ remux_restampdts, "uiport:"+ uiport, "remux_pcr:"+ remux_pcr,
		"dec_type:"+ dec_type, "cols:"+ cols);
		
		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet("http://" + loin_ip + ":" + uiport + "/login.htm", userName , userPass, loin_ip, uiport);
		
		String request = "http://" + loin_ip + ":" + uiport + "/zixi/add_output.json?" + "type" + "=" + type + "&" + "id" + "=" + id  + "&" + "name" + "=" + streamname + 
		"&stream=" + stream + "&host=" + host + "&port=" + port + "&smoothing=" + smoothing + "&ttl=" + ttl + "&df=" + df + "&local-port=" + local_port + "&local-ip=" + local_ip + 
		"&dec-type=" + dec_type + "&dec-key=" + dec_key + "&rtp=" + rtp + "&fec=" + fec + "&rows=" + rows + "&cols=" + cols + "&remux_bitrate=" + remux_bitrate + 
		"&remux_pcr=" + remux_pcr + "&remux_buff=" + remux_buff + "&remux_restampdts=" + remux_restampdts;
		
		
		testFlowDescriptor.append("\nAPI equest is " + request);
		
		return apiworker.sendGet(request, id, UDPOUTMODE, responseCookieContainer, loin_ip, this, uiport);
	}
}
