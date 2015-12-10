package com.zixi.niostuff;

import java.nio.ByteBuffer;

public class BufferByte {
	protected static int size = 10;
	protected ByteBuffer buf = ByteBuffer.allocate(size);
	
	public ByteBuffer getBuf() {
		return buf;
	}

	public void setBuf(ByteBuffer buf) {
		this.buf = buf;
	}

	public static void main(String[] args)
	{
		
		// Note: a newly created buffer has a "capacity" equals to "limit" .
		BufferByte bb = new  BufferByte();
		
		System.out.println("The position is " + bb.getBuf().position());
		System.out.println( "The capacity is " + bb.getBuf().capacity());
		System.out.println( "The limit is "+ bb.getBuf().limit());
		bb.getBuf().put((byte)'H').put((byte)'e').put((byte)'l').put((byte)'l').put((byte)'o');
		bb.getBuf().flip();
		
		System.out.println("-------------------------------");
		System.out.println("The position is " + bb.getBuf().position());
		System.out.println( "The capacity is " + bb.getBuf().capacity());
		System.out.println( "The limit is "+ bb.getBuf().limit());
		// Buffer has a states: 1 filling state, drain
		
		// buffer.limit(buffer.position( )).position(0); ====== buffer.flip( );
		
		
		System.out.println("-------------------------------");
		bb.getBuf().get();
		
		System.out.println("The position is " + bb.getBuf().position());
		System.out.println( "The capacity is " + bb.getBuf().capacity());
		System.out.println( "The limit is "+ bb.getBuf().limit());
		
		System.out.println("-------------------------------");
		bb.getBuf().get();

		System.out.println("The position is " + bb.getBuf().position());
		System.out.println( "The capacity is " + bb.getBuf().capacity());
		System.out.println( "The limit is "+ bb.getBuf().limit());
		
		
		System.out.println("-------------------------------");
		bb.getBuf().put((byte)'H');

		System.out.println("The position is " + bb.getBuf().position());
		System.out.println( "The capacity is " + bb.getBuf().capacity());
		System.out.println( "The limit is "+ bb.getBuf().limit());
	}
}
