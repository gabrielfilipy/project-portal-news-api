package com.monet.portal.domain.fotos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.monet.portal.domain.model.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "tbl_foto_user")
public class UserFoto {

	@Id
	@Column(name = "user_id_user")
	private Long idUser;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private User user;
	
	private String nameFile;
	private String description;
	private String contentType;
	private Long size;
	
}
