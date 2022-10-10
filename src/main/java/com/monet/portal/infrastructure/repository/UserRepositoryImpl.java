package com.monet.portal.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.monet.portal.domain.fotos.UserFoto;
import com.monet.portal.domain.repository.UserRepositoryQueries;

@Repository
public class UserRepositoryImpl implements UserRepositoryQueries{

	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public UserFoto save(UserFoto foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void remove(UserFoto foto) {
		System.out.println(">>>>> FOTO: "  + foto.getIdUser());
		manager.remove(foto);
	}
	
}
