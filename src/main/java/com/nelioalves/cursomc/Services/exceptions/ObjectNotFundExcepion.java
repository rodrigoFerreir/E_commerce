package com.nelioalves.cursomc.Services.exceptions;

public class ObjectNotFundExcepion extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	
	public ObjectNotFundExcepion(String msg) {
		super(msg);
	}
	
	public ObjectNotFundExcepion(String msg, Throwable cause) {
		super(msg, cause);
	}

}
