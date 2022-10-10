package com.monet.portal.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "categorys")
@Getter
@Setter
public class CategoryDTO extends RepresentationModel<CategoryDTO> {

	private Long idCategory;
	private String description;
	private String title;
	private Boolean active;
	
}
