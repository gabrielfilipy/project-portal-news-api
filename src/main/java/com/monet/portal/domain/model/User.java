package com.monet.portal.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_user")
public class User { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	private String nameUser;
	private String passwordUser;
	private String aboutUser;
	private String professionUser;
	private String photoUser;
	
	private Boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "code_user"), 
	inverseJoinColumns = @JoinColumn(name = "code_permission"))
	private List<Permission> permissions = new ArrayList<>();
	
}
