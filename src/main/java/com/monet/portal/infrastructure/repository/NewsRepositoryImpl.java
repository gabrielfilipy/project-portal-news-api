package com.monet.portal.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.monet.portal.domain.fotos.NewsFoto;
import com.monet.portal.domain.fotos.UserFoto;
import com.monet.portal.domain.repository.NewsRepositoryQueries;
import com.monet.portal.domain.repository.UserRepositoryQueries;

@Repository
public class NewsRepositoryImpl implements NewsRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public NewsFoto save(NewsFoto foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void remove(NewsFoto foto) {
		System.out.println(">>>>> FOTO: "  + foto.getIdNews());
		manager.remove(foto);
	}
	
}
