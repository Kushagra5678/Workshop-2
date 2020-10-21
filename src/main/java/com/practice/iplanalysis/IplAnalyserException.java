package com.practice.iplanalysis;

public class IplAnalyserException extends Exception {
	
	enum ExceptionType{
		WRONG_PATH;
	}
	
	public ExceptionType type;
	
	public IplAnalyserException(String msg, ExceptionType type) {
		super(msg);
		this.type = type;
	}
}


