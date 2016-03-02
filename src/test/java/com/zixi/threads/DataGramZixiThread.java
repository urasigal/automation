package com.zixi.threads;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DataGramZixiThread extends Thread{
	
	protected DatagramSocket socket = null;
	protected int port;
	protected byte[] buf = new byte[1316];
	protected List<Long> times;
	protected ArrayList<Long> diff; 
	protected boolean notStop ;
	protected long result;
	
	public long getResult() {
		return result;
	}


	public void setNotStop(boolean notStop) {
		this.notStop = notStop;
	}


	public DataGramZixiThread(int port)
	{
		this.port = port;
		try {
			socket = new DatagramSocket(port);
			socket.setSoTimeout(0);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public void run()
	 {
		 notStop = true;
		 times = new ArrayList<Long>();
		 diff = new ArrayList<Long>();
		 while(notStop)
		 {
			 DatagramPacket packet = new DatagramPacket(buf, buf.length);
	
			 try {
				socket.receive(packet); // block here till packet is arrived. 
				times.add(System.currentTimeMillis());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 int listSize = times.size();
		 listSize -- ;
		 for(int i = 0 ; i < listSize; i ++)
		 {
			 diff.add(times.get(i + 1) - times.get(i)); 
		 }
		 diff.sort(
					 new Comparator<Long>() 
					 {
						 	public int compare(Long o1, Long o2) 
						 {
						 		return o2.compareTo(o1);
						 }
				     }
				 );
		 System.out.println("Max is " + diff.get(0)) ;
		 result = diff.get(0);
		 socket.close();
	 }
}
