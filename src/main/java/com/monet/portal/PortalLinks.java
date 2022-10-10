package com.monet.portal;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.monet.portal.controller.CategoryController;
import com.monet.portal.controller.NewsController;

@Component
public class PortalLinks {

	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));
	
	public Link linkToCategory(Long id) { 
		return WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(CategoryController.class)
						.returnId(id)).withSelfRel();
	}
	
	public Link linkToCategory(String rel) { 
		TemplateVariables pageVariables = new TemplateVariables(
				new TemplateVariable("description", VariableType.REQUEST_PARAM),
				new TemplateVariable("page", VariableType.REQUEST_PARAM),
				new TemplateVariable("size", VariableType.REQUEST_PARAM),
				new TemplateVariable("sort", VariableType.REQUEST_PARAM));
		
		String url = WebMvcLinkBuilder.linkTo(CategoryController.class).toUri().toString();
		
		return Link.of(UriTemplate.of(url,pageVariables), "category");
	}
	
	public Link linkToNews(Long id) { 
		return WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(NewsController.class)
						.returnId(id)).withSelfRel();
	}
	
	public Link linkToNews(String rel) { 
		TemplateVariables pageVariables = new TemplateVariables(
				new TemplateVariable("title", VariableType.REQUEST_PARAM),
				new TemplateVariable("date", VariableType.REQUEST_PARAM),
				new TemplateVariable("active", VariableType.REQUEST_PARAM),
				new TemplateVariable("page", VariableType.REQUEST_PARAM),
				new TemplateVariable("size", VariableType.REQUEST_PARAM),
				new TemplateVariable("sort", VariableType.REQUEST_PARAM));
		
		String url = WebMvcLinkBuilder.linkTo(NewsController.class).toUri().toString();
		
		return Link.of(UriTemplate.of(url,pageVariables), "news");
	}
	
}
