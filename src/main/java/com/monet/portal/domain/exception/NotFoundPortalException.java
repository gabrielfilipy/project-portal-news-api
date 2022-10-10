package com.monet.portal.domain.exception;

public class NotFoundPortalException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotFoundPortalException(String msg) {
		super(msg);
	}
	
}
