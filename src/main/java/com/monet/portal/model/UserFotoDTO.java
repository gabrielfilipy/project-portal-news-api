package com.monet.portal.model;

import com.monet.portal.domain.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFotoDTO {

	private User user;
	private String nameFile;
	private String description;
	private String contentType;
	private Long size;
	
}
