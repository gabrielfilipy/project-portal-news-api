package com.monet.portal.model;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.monet.portal.domain.model.Category;
import com.monet.portal.domain.model.User;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "news")
@Getter
@Setter
public class NewsDTO extends RepresentationModel<NewsDTO> {

	private Long idNews;
	private String title;
	private String content;
	private String photoNews;
	private LocalDate date;
	private Boolean active;
	private Category category;
	private User user;
	
}
