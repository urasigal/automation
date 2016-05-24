package com.zixi.tools;

@FunctionalInterface
public interface Prerequisitor {
	public void setToExecutionLevel(String... args);
}
