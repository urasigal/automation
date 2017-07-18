package com.zixi.g1050test;

import org.testng.Assert;
import com.zixi.drivers.drivers.BroadcaserSingleOutputStreamDeletionDriver;
import com.zixi.drivers.drivers.BroadcasterPushG1050Driver;

public class BroadcasterPushG1050Test {
	public static void main(String [] args){
		
		BroadcasterPushG1050Driver testDriver = new BroadcasterPushG1050Driver();
		
		String sutProcessId = BroadcasterPushG1050Driver.getPid("maxwell",  "maxwell",  "10.7.0.70",  "22",  "pidof zixi_broadcaster");
	}
}
