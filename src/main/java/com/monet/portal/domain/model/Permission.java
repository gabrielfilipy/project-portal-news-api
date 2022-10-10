package com.monet.portal.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_permission")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPermission;
	private String description;
	
}
