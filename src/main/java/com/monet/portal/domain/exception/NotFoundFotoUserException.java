package com.monet.portal.domain.exception;

public class NotFoundFotoUserException  extends NotFoundPortalException{

	private static final long serialVersionUID = 1L;

	public NotFoundFotoUserException(String message) {
		super(message);
	}

	public NotFoundFotoUserException(Long idUser) {
		this(String.format("There is no photo for this user with the code %d", idUser));
	}
	
}
