package com.zixi.testing.util;

public abstract class GenericImageTable<T> {
	
	private T img [][];
	
	public GenericImageTable( T [][] img)
	{
		this.img = img;
		
	}
}
