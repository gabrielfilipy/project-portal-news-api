package com.monet.portal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.monet.portal.domain.model.User;
import com.monet.portal.domain.repository.UserRepository;
import com.monet.portal.domain.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping()
	public List<User> list () {
		return repository.findAll();
	}
	
	@PostMapping()
	public ResponseEntity<User> add(@RequestBody @Valid User user) {
		repository.save(user);  
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@GetMapping("/{idUser}")
	public ResponseEntity<?> returnId(@PathVariable Long idUser) {
		Optional<User> user = repository.findById(idUser);
		if(user.isPresent()){
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PutMapping("/{idUser}")
	public ResponseEntity<?> update(@Valid @RequestBody User user, @PathVariable Long idUser) {	
		User userSave = service.update(idUser, user);
		if(userSave != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{idUser}")
	public ResponseEntity<?> remove(@PathVariable Long idUser) {
		Optional<User> user = repository.findById(idUser);
		if (user.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else {
			repository.deleteById(idUser);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PutMapping("/{idUser}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSituationUser(@PathVariable Long idUser, @RequestBody Boolean ativo) {
		service.updateSituationUser(idUser, ativo);
	}
	
} 
