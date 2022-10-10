package com.monet.portal.domain.exception;

public class BusinessRulePortalException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BusinessRulePortalException(String message) {
		super(message);
	}
	
	public BusinessRulePortalException(String messsage, Throwable cases) {
		super(messsage, cases);
	}
	
}
