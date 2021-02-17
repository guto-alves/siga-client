package com.gutotech.sigaclient.exeception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		super("Resource not found.");
	}

}
