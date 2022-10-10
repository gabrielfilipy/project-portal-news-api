package com.monet.portal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.monet.portal.assembler.CategoryModelAssembler;
import com.monet.portal.domain.exception.BusinessRulePortalException;
import com.monet.portal.domain.exception.EntityUsedPortalException;
import com.monet.portal.domain.model.Category;
import com.monet.portal.domain.repository.CategoryRepository;
import com.monet.portal.domain.service.CategoryService;
import com.monet.portal.model.CategoryDTO;
import com.monet.portal.util.URIHelperPortal;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;
	
	@Autowired 
	private CategoryService service;
	
	@Autowired
	private CategoryModelAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<Category> pagedDTOAssembler;
	
	@GetMapping
	public PagedModel<CategoryDTO> collection(@RequestParam String description, 
			@PageableDefault(size = 5) Pageable pageable) {
		Page<Category> categorysPage = repository.collectionToDescription(description, pageable);
		PagedModel<CategoryDTO> pagedModelCategory = 
				pagedDTOAssembler.toModel(categorysPage, assembler);
		return pagedModelCategory;
	}
	
	@PostMapping()
	public ResponseEntity<Category> add(@RequestBody @Valid Category category) {
		try {
			
			Category categorySave = repository.save(category);  
			
			//View URI in Header/Location.
			URIHelperPortal.addURIInHeader(categorySave.getIdCategory());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(category);
		} catch (Exception e) { 
			throw new BusinessRulePortalException(e.getMessage(), e); 
		}
	}
	
	@GetMapping("/{id}")
	public CategoryDTO returnId(@PathVariable Long id) {
		Category category = service.searchOrFailure(id);
		return assembler.toModel(category);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Category category, @PathVariable Long id) {	
		Category categorySave = service.update(id, category);
		if(categorySave != null) 
			return ResponseEntity.status(HttpStatus.CREATED).body(categorySave);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			Category category = service.searchOrFailure(id);
			if(category != null) 
				service.delete(id);
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			throw new EntityUsedPortalException(e.getMessage());
		}
	}
	
	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSituationCategory(@PathVariable Long id, @RequestBody Boolean active) {
		service.updateSituationUser(id, active);
	}
	
}
