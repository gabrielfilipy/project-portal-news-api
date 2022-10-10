package com.monet.portal.model;

import com.monet.portal.domain.model.News;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsFotoDTO {

	private News news;
	private String nameFile;
	private String description;
	private String contentType;
	private Long size;
	
}
