package com.monet.portal.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.monet.portal.PortalLinks;
import com.monet.portal.controller.CategoryController;
import com.monet.portal.domain.model.Category;
import com.monet.portal.model.CategoryDTO;

@Component
public class CategoryModelAssembler 
		extends RepresentationModelAssemblerSupport<Category, CategoryDTO>{

	//HATEOS
	public CategoryModelAssembler() {
		super(CategoryController.class, CategoryDTO.class);
	}

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PortalLinks portalLinks;
	
	public CategoryDTO toModel(Category category) {
		CategoryDTO categoryDTO =
				modelMapper.map(category, CategoryDTO.class);
	
		//HATEOS
		categoryDTO.add(portalLinks.linkToCategory(categoryDTO.getIdCategory()));
		categoryDTO.add(portalLinks.linkToCategory(""));
		
//		categoryDTO.add(Link.of("", ""));
		
		return categoryDTO;
	}
	
//	public List<CategoryDTO> toCollectionModel(List<Category> collection) {
//		return collection.stream()
//					.map(m -> toModel(m))
//					.collect(Collectors.toList());
//	}
	
}
