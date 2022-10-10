package com.monet.portal.domain.exception;

public class NotFoundUserException  extends NotFoundPortalException{

	private static final long serialVersionUID = 1L;

	public NotFoundUserException(String message) {
		super(message);
	}

	public NotFoundUserException(Long idUser) {
		this(String.format("There is no user with this code: %d", idUser));
	}
	
}
