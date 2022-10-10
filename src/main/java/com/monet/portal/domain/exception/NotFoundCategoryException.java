package com.monet.portal.domain.exception;

public class NotFoundCategoryException  extends NotFoundPortalException{

	private static final long serialVersionUID = 1L;

	public NotFoundCategoryException(String message) {
		super(message);
	}

	public NotFoundCategoryException(Long idCategory) {
		this(String.format("There is no category with this code: %d", idCategory));
	}
	
}
