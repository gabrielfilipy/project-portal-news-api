package com.monet.portal.domain.repository;

import com.monet.portal.domain.fotos.UserFoto;

public interface UserRepositoryQueries {

	UserFoto save (UserFoto foto);
	void remove (UserFoto foto);
	
}
