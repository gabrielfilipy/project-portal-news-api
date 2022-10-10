package com.monet.portal.domain.exception;

public class NotFoundNewsException  extends NotFoundPortalException{

	private static final long serialVersionUID = 1L;

	public NotFoundNewsException(String message) {
		super(message);
	}

	public NotFoundNewsException(Long idUser) {
		this(String.format("There is no News with this code: %d", idUser));
	}
	
}
