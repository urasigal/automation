package com.zixi.entities;

public class StreamEntity {
	
	private final String width;

	private final String hight;
	private final String progressive;
	private final String fps;
	private final String audioRate;
	private final String videoCodec;
	private final String audioCodec;
	
	public StreamEntity(String width, String hight, String progressive,String fps, String audioRate, String videoCodec, String audioCodec)
	{
		this.width = width;
		this.hight = hight;
		this.progressive = progressive;
		this.fps = fps;
		this.audioRate = audioRate;
		this.videoCodec = videoCodec;
		this.audioCodec = audioCodec;
	}
	
	public String getWidth() {
		return width;
	}

	public String getHight() {
		return hight;
	} 

	public String getProgressive() {
		return progressive;
	}

	public String getFps() {
		return fps;
	}

	public String getAudioRate() {
		return audioRate;
	}

	public String getVideoCodec() {
		return videoCodec;
	}

	public String getAudioCodec() {
		return audioCodec;
	}

}
