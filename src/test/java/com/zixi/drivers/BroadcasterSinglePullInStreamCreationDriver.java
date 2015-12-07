package com.zixi.drivers;

import static com.zixi.globals.Macros.PULLMODE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.zixi.entities.StreamEntity;
import com.zixi.entities.TestParameters;
import com.zixi.tools.ApiWorkir;
import com.zixi.tools.BroadcasterLoggableApiWorker;

public class BroadcasterSinglePullInStreamCreationDriver extends
		BroadcasterLoggableApiWorker implements TestDriver {
	

	private StreamEntity streamEntity;
	private InputStream input = null;
	private Properties prop = null;
	private ApiWorkir streamCreator = new ApiWorkir();

	final private static String HTTP = "http://";
	final private static String params1 = ":";
	final private static String params2 = "&";
	final private static String params4 = "=";
	final private static String params5 = "ie_fooler=0.45086039789021015";
	final private static String params7 = "/zixi/add_stream.json?";

	final private String rfunc = "func";
	final private String ron = "on";
	final private String rpassword = "password";
	final private String rlatency = "latency";
	final private String rtype = "type";
	final private String rid = "id";
	final private String rmax_outputs = "max_outputs";
	final private String rmcast_out = "mcast_out";
	final private String rmcast_force = "mcast_force";
	final private String rmcast_ip = "mcast_ip";
	final private String rmcast_port = "mcast_port";
	final private String rmcast_ttl = "mcast_ttl";
	final private String rtime_shift = "time_shift";
	final private String rcomplete = "complete";
	final private String rpull_port = "pull-port";
	final private String rsource = "source";
	final private String rfec_overhead = "fec_overhead";
	final private String rfec_latency = "fec_latency";
	final private String rfec_aware = "fec_aware";
	final private String rfec_adaptive = "fec_adaptive";
	final private String rfec_force = "fec_force";
	final private String rnic = "nic";
	final private String rhost0 = "host0";

	public String testIMPL(String userName, String userPass, String Host,
			String loin_ip, String id, String source, String uiport,
			String pull_port, String latency, String fec_latency,
			String fec_overhead, String mcast_force, String time_shift,
			String nic, String max_outputs, String type, String password,
			String mcast_port, String complete, String mcast_ip,
			String fec_adaptive, String mcast_ttl, String on, String func,
			String fec_force, String mcast_out, String propertiesFile) {
		
		testParameters = new TestParameters("userName:" + userName, "userPass:"
				+ userPass, "Host:" + Host, "loin_ip:" + loin_ip, "id:" + id,
				"source:" + source, "uiport:" + uiport, "pull_port:"
						+ pull_port, "latency:" + latency, "fec_latency:"
						+ fec_latency, "fec_overhead:" + fec_overhead,
				"mcast_force:" + mcast_force, "time_shift:" + time_shift,
				"nic:" + nic, "max_outputs:" + max_outputs, "type:" + type,
				"password:" + password, "mcast_port:" + mcast_port, "complete:"
						+ complete, "mcast_ip:" + mcast_ip, "fec_adaptive:"
						+ fec_adaptive, "mcast_ttl:" + mcast_ttl, "on:" + on,
				"func:" + func, "fec_force:" + fec_force, "mcast_out:"
						+ mcast_out, "propertiesFile:" + propertiesFile);

		try {
			input = new FileInputStream(propertiesFile);
			prop = new Properties();
			prop.load(input);
			streamEntity = new StreamEntity(prop.getProperty("width"),
					prop.getProperty("hight"),
					prop.getProperty("progressivness"),
					prop.getProperty("fps"), prop.getProperty("audiobitrate"),
					prop.getProperty("videocodec"),
					prop.getProperty("audiocodec"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + loin_ip + ":" + uiport + "/login.htm", userName,
				userPass, loin_ip, uiport);
		// this function creates a single pull stream
		return createPullInWithRandomName(userName, userPass, Host, loin_ip,
				id, source, uiport, pull_port, latency, fec_latency,
				fec_overhead, mcast_force, time_shift, nic, max_outputs, type,
				password, mcast_port, complete, mcast_ip, fec_adaptive,
				mcast_ttl, on, func, fec_force, mcast_out);
	}

	public String createPullInWithRandomName(String userName, String userPass,
			String Host, String loin_ip, String id, String source,
			String uiport, String pull_port, String latency,
			String fec_latency, String fec_overhead, String mcast_force,
			String time_shift, String nic, String max_outputs, String type,
			String password, String mcast_port, String complete,
			String mcast_ip, String fec_adaptive, String mcast_ttl, String on,
			String func, String fec_force, String mcast_out) {
		return streamCreator.sendGet(HTTP + loin_ip + params1 + uiport
				+ params7 + rfunc + params4 + func + params2 + rtype + params4
				+ type + params2 + rid + params4 + id + params2 + rmax_outputs
				+ params4 + max_outputs + params2 + rmcast_out + params4
				+ mcast_out + params2 + rmcast_force + params4 + mcast_force
				+ params2 + rmcast_ip + params4 + mcast_ip + params2
				+ rmcast_port + params4 + mcast_port + params2 + rmcast_ttl
				+ params4 + mcast_ttl + params2 + rtime_shift + params4
				+ time_shift + params2 + rhost0 + params4 + Host + params2
				+ rpull_port + params4 + pull_port + params2 + rsource
				+ params4 + source + params2 + rpassword + params4 + password
				+ params2 + rlatency + params4 + "6000" + params2 + rnic
				+ params4 + nic + params2 + rfec_overhead + params4
				+ fec_overhead + params2 + rfec_latency + params4 + fec_latency
				+ params2 + rfec_aware + params4 + "" + params2 + rfec_adaptive
				+ params4 + fec_adaptive + params2 + rfec_force + params4
				+ fec_force + params2 + rcomplete + params4 + complete
				+ params2 + ron + params4 + on + params2 + params5, id,
				PULLMODE, responseCookieContainer, loin_ip, this, uiport);
	}
}
