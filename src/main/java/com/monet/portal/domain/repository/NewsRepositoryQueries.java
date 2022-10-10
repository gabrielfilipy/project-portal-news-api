package com.monet.portal.domain.repository;

import com.monet.portal.domain.fotos.NewsFoto;

public interface NewsRepositoryQueries {

	NewsFoto save (NewsFoto foto);
	void remove (NewsFoto foto);
	
}
