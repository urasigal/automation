package com.zixi.drivers;

import com.zixi.entities.TestParameters;
import com.zixi.tools.BroadcasterLoggableApiWorker;

import static com.zixi.globals.Macros.*;

public class BroadcasterAddTranscoderProfileDriver extends
		BroadcasterLoggableApiWorker implements TestDriver {

	public String testIMPL(String userName, String userPass, String login_ip,
			String uiport, String profile_name, String enc, String bitrate,
			String gop, String fixed_gop, String closed_gop,
			String performance, String b_frames, String frame_type,
			String profile, String level, String bitrate_mode,
			String ref_frames, String idr_int, String cavlc, String brightness,
			String contrast, String fps, String width, String height,
			String max_bitrate) {

		testParameters = new TestParameters("userName:" + userName, "userPass:"
				+ userPass, "login_ip:" + login_ip, "uiport:" + uiport,
				"profile_name:" + profile_name, "enc:" + enc, "bitrate:"
						+ bitrate, "gop:" + gop, "fixed_gop:" + fixed_gop,
				"closed_gop:" + closed_gop, "performance:" + performance,
				"b_frames:" + b_frames, "frame_type:" + frame_type, "profile:"
						+ profile, "level:" + level, "bitrate_mode:"
						+ bitrate_mode, "ref_frames:" + ref_frames, "idr_int:"
						+ idr_int, "cavlc:" + cavlc,
				"brightness:" + brightness, "contrast:" + contrast, "fps:"
						+ fps, "width:" + width, "height:" + height,
				"max_bitrate:" + max_bitrate);

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		return apiworker.sendGet("http://" + login_ip + ":"
				+ uiport + "/zixi/add_h264_profile.json?" + rprofile_name
				+ profile_name + "&" + renc + enc + "&" + rbitrate + bitrate
				+ "&" + rgop + gop + "&" + rfixed_gop + fixed_gop + "&"
				+ rclosed_gop + closed_gop + "&" + rperformance + performance
				+ "&" + rb_frames + b_frames + "&" + rframe_type + frame_type
				+ "&" + rprofile + profile + "&" + rlevel + level + "&"
				+ rbitrate_mode + bitrate_mode + "&" + rref_frames + ref_frames
				+ "&" + ridr_int + idr_int + "&" + rcavlc + cavlc + "&"
				+ rbrightness + brightness + "&" + rcontrast + contrast + "&"
				+ rfps + fps + "&" + rwidth + width + "&" + rheight + height
				+ "&" + rmax_bitrate + max_bitrate, "", ADD_TRANSCODER_PROFILE,
				responseCookieContainer, login_ip, this, uiport);
		
	}
	
	public String testIMPL(String userName, String userPass, String login_ip, String uiport,String mode, String profile_name,
			String enc, String bitrate, String gop, String fixed_gop, String closed_gop, String copy_gop, String performance,
			String b_frames, String frame_type, String profile, String level, String bitrate_mode,
			String ref_frames, String hrd, String idr_int, String cavlc, String brightness, String contrast, String fps,
			String width, String height, String crf, String tune, String max_bitrate) {

		testParameters = new TestParameters("userName:" + userName, "userPass:"
				+ userPass, "login_ip:" + login_ip, "uiport:" + uiport,
				"profile_name:" + profile_name, "enc:" + enc, "bitrate:"
						+ bitrate, "gop:" + gop, "fixed_gop:" + fixed_gop,
				"closed_gop:" + closed_gop, "copy_gop:" + copy_gop,  "performance:" + performance,
				"b_frames:" + b_frames, "frame_type:" + frame_type, "profile:"
						+ profile, "level:" + level, "bitrate_mode:"
						+ bitrate_mode, "ref_frames:" + ref_frames, "hrd:" + hrd, "idr_int:"
						+ idr_int, "cavlc:" + cavlc,
				"brightness:" + brightness, "contrast:" + contrast, "fps:"
						+ fps, "width:" + width, "height:" + height,
				"max_bitrate:" + max_bitrate);

		responseCookieContainer = broadcasterInitialSecuredLogin.sendGet(
				"http://" + login_ip + ":" + uiport + "/login.htm", userName,
				userPass, login_ip, uiport);

		
		if (mode.equals("h.264") || mode.equals("mpeg2") || mode.equals("h.265"))
		{
		return apiworker.sendGet("http://" + login_ip + ":"
				+ uiport + "/zixi/add_h264_profile.json?" + rprofile_name
				+ profile_name + "&" + renc + enc + "&" + rbitrate + bitrate
				+ "&" + rgop + gop + "&" + rfixed_gop + fixed_gop + "&"
				+ rclosed_gop + closed_gop + "&" +  "copy_gop=" + copy_gop + "&" + rperformance + performance
				+ "&" + rb_frames + b_frames + "&" + rframe_type + frame_type
				+ "&" + rprofile + profile + "&" + rlevel + level + "&"
				+ rbitrate_mode + bitrate_mode + "&" + rref_frames + ref_frames
				+ "&" + "hrd=" + hrd + "&" + ridr_int + idr_int + "&" + rcavlc + cavlc + "&"
				+ rbrightness + brightness + "&" + rcontrast + contrast + "&"
				+ rfps + fps + "&" + rwidth + width + "&" + rheight + height
				+ "&" + "crf=" + crf + "&tune=" + tune +"&" + rmax_bitrate + max_bitrate, "", ADD_TRANSCODER_PROFILE,
				responseCookieContainer, login_ip, this, uiport);
		}
		return "";
		
	}

	static private final String rprofile_name = "profile_name=";
	static private final String renc = "enc=";
	static private final String rbitrate = "bitrate=";
	static private final String rgop = "gop=";
	static private final String rfixed_gop = "fixed_gop=";
	static private final String rclosed_gop = "closed_gop=";
	static private final String rperformance = "performance=";
	static private final String rb_frames = "b_frames=";
	static private final String rframe_type = "frame_type=";
	static private final String rprofile = "profile=";
	static private final String rlevel = "level=";
	static private final String rbitrate_mode = "bitrate_mode=";
	static private final String rref_frames = "ref_frames=";
	static private final String ridr_int = "idr_int=";
	static private final String rcavlc = "cavlc=";
	static private final String rbrightness = "brightness=";
	static private final String rcontrast = "contrast=";
	static private final String rfps = "fps=";
	static private final String rwidth = "width=";
	static private final String rheight = "height=";
	static private final String rmax_bitrate = "max_bitrate=";
}
