package com.monet.portal.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.monet.portal.domain.fotos.UserFoto;
import com.monet.portal.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryQueries{

	@Query("select f from UserFoto f join f.user u where u.idUser = :userId")
	Optional<UserFoto> findFotoById(Long userId);
	
}
