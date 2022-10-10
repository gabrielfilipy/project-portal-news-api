package com.monet.portal.domain.service;


import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monet.portal.domain.exception.NotFoundUserException;
import com.monet.portal.domain.model.User;
import com.monet.portal.domain.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Transactional
	public User update(Long idUser, User user) {
		User userSave = searchOrFailure(idUser);
		if (userSave != null) {
			BeanUtils.copyProperties(user, userSave, "idUser");
			return repository.save(userSave);
		}
		return null;
	}
	
	//partial update implementation
	public void updateSituationUser(Long id, Boolean active) {
		User userSave = searchOrFailure(id);
		if(userSave != null){
			userSave.setActive(active);
			repository.save(userSave);
		}
	}
	
	public User searchOrFailure(Long idUser) {
		return repository.findById(idUser)
				.orElseThrow(() -> new NotFoundUserException(idUser));
	}
	
}
