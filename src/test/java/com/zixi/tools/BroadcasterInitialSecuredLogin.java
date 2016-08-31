package com.zixi.tools;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;




import org.apache.commons.lang3.StringUtils;

public class BroadcasterInitialSecuredLogin 
{
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0";

	/**
	 * Http GET request.
	 * @param      url      		consists of a regular GET request to a broadcaster 
	 * @param      user   			user name of a broadcaster user
	 * @param      pass    			user password of a broadcaster user.
	 * @param 	   bX_IP_ADDRESS    destination broadcaster IP address.
	 * @param 	   uiport 			broadcaster UI port
	 * @throws Exception 
	 */
	public String[] sendGet(String url, String user, String pass, String bX_IP_ADDRESS, String uiport) throws Exception {
		String sessionID = null;
		String responseCookieContainer[] = new String[2];
		try {
			System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
			StringBuilder postData = new StringBuilder();
			postData.append("user=" + user + "&pass=" + pass);
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setInstanceFollowRedirects(false); 

			con.setRequestProperty("Host", bX_IP_ADDRESS + ":" + uiport);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.04");
			
			con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Referer", "http://" +  bX_IP_ADDRESS + ":" + uiport + "/login.html");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestProperty("Content-Length", "20");

			con.setDoOutput(true);
			con.getOutputStream().write(postDataBytes);

			// get all headers
			Map<String, List<String>> map = con.getHeaderFields();
			
			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				if (entry.getKey() != null) {
					if (entry.getKey().equals("Set-Cookie")) {
						//System.out.println(entry.getValue().get(0));
						String sessioinIdCookie = StringUtils.substringBefore(entry
								.getValue().get(1), ";"); 
						//System.out.println(sessioinIdCookie);
						responseCookieContainer[0] = entry.getValue().get(0);
					    responseCookieContainer[1] = sessioinIdCookie;
					}
					
				}	
			}
//			System.out.println("one  : " + responseCookieContainer[0] + " ,two: "
//					+  responseCookieContainer[1]);	
//			sessionID = con.getHeaderField("Set-Cookie");
			//System.out.println(sessionID);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(responseCookieContainer[0] == null || responseCookieContainer[1] == null)
		{
			throw new Exception(){
				public String getMessage()
				{
					return "Loggin parameters are empty.";
				}
			};
		}
		return responseCookieContainer;
	}
}