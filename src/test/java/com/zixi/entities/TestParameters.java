package com.zixi.entities;

import org.testng.Reporter;

public class TestParameters {
	
	protected String analyze;
	protected String complete;
	protected String force_p2p;
	protected String id;
	protected String latency;
	protected String login_ip;
	protected String max_outputs;
	protected String mcast_force;
	protected String mcast_ip;
	protected String mcast_out;
	protected String mcast_port;
	protected String mcast_ttl;
	protected String on;
	protected String password;
	protected String time_shift;
	protected String type;
	protected String uiport;
	protected String userName;
	protected String userPass;
	
	public TestParameters(){}
	
	public TestParameters(String userName,String userPass,String login_ip,String latency,String time_shift,String force_p2p,String mcast_ip,String mcast_force,String mcast_port,String type,
			String uiport,String analyze,String mcast_ttl,String id,String mcast_out,String complete,String max_outputs,String on, String password)
	{
		setAnalyze(analyze);
		setComplete(complete);
		setForce_p2p(force_p2p);
		setId(id);
		setLatency(latency);
		setLogin_ip(login_ip);
		setMax_outputs(max_outputs);
		setMcast_force(mcast_force);
		setMcast_ip(mcast_ip);
		setMcast_out(mcast_out);
		setMcast_port(mcast_port);
		setMcast_ttl(mcast_ttl);
		setOn(on);
		setPassword(password);
		setTime_shift(time_shift);
		setType(type);
		setUiport(uiport);
		setUserName(userName);
		setUserPass(userPass);
	}
	
	public TestParameters(String... args)
	{
		Reporter.log("Test parameteres: ");
		for(String param:args)
		{
			String[] params = param.split(":");
			if(params.length == 2)
				Reporter.log(params[0] + ": " + params[1] );
			else
				Reporter.log(params[0] + ": -- not provided " );
		}
	}
	
	public void  printParametersToHTML()
	{
		Reporter.log("Test parameteres: ");
		Reporter.log("analyze " + analyze);
		Reporter.log("complete " + complete);
		Reporter.log("force_p2p " + force_p2p);
		Reporter.log("id " + id);
		Reporter.log("latency " + latency);
		Reporter.log("login_ip " + login_ip);
		Reporter.log("max_outputs " + max_outputs);
		Reporter.log("mcast_force " + mcast_force);
		Reporter.log("mcast_ip " + mcast_ip );
		Reporter.log("mcast_out " +  mcast_out);
		Reporter.log("mcast_port " + mcast_port);
		Reporter.log("mcast_ttl " +  mcast_ttl);
		Reporter.log("on " + on );
		Reporter.log("password " + password );
		Reporter.log("time_shift " + time_shift);
		Reporter.log("type " + type);
		Reporter.log("uiport " + uiport);
		Reporter.log("userName " + userName);
		Reporter.log("userPass" + userPass);
		
	}
	
	public String getAnalyze() {
		return analyze;
	}
	public String getComplete() {
		return complete;
	}
	public String getForce_p2p() {
		return force_p2p;
	}
	public String getId() {
		return id;
	}
	public String getLatency() {
		return latency;
	}
	public String getLogin_ip() {
		return login_ip;
	}
	public String getMax_outputs() {
		return max_outputs;
	}
	public String getMcast_force() {
		return mcast_force;
	}
	public String getMcast_ip() {
		return mcast_ip;
	}
	public String getMcast_out() {
		return mcast_out;
	}
	public String getMcast_port() {
		return mcast_port;
	}
	public String getMcast_ttl() {
		return mcast_ttl;
	}
	public String getOn() {
		return on;
	}
	public String getPassword() {
		return password;
	}
	public String getTime_shift() {
		return time_shift;
	}
	public String getType() {
		return type;
	}
	public String getUiport() {
		return uiport;
	}
	public String getUserName() {
		return userName;
	}
	public String getUserPass() {
		return userPass;
	}
	
	
	public void setAnalyze(String analyze) {
		this.analyze = analyze;
	}
	public void setComplete(String complete) {
		this.complete = complete;
	}
	public void setForce_p2p(String force_p2p) {
		this.force_p2p = force_p2p;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setLatency(String latency) {
		this.latency = latency;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public void setMax_outputs(String max_outputs) {
		this.max_outputs = max_outputs;
	}
	public void setMcast_force(String mcast_force) {
		this.mcast_force = mcast_force;
	}
	public void setMcast_ip(String mcast_ip) {
		this.mcast_ip = mcast_ip;
	}
	public void setMcast_out(String mcast_out) {
		this.mcast_out = mcast_out;
	}
	public void setMcast_port(String mcast_port) {
		this.mcast_port = mcast_port;
	}
	public void setMcast_ttl(String mcast_ttl) {
		this.mcast_ttl = mcast_ttl;
	}
	public void setOn(String on) {
		this.on = on;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setTime_shift(String time_shift) {
		this.time_shift = time_shift;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUiport(String uiport) {
		this.uiport = uiport;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
}
