package com.monet.portal.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monet.portal.domain.model.Category;
import com.monet.portal.domain.exception.NotFoundCategoryException;
import com.monet.portal.domain.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	@Transactional
	public Category update(Long idCategory, Category category) {
		Category categorySave = searchOrFailure(idCategory);
		if (categorySave != null) {
			BeanUtils.copyProperties(category, categorySave, "idCategory");
			return repository.save(categorySave);
		}
		return null;
	}
	
	//partial update implementation
	public void updateSituationUser(Long id, Boolean active) {
		Category categorySave = searchOrFailure(id);
		if(categorySave != null){
			categorySave.setActive(active);
			repository.save(categorySave);
		}
	}
	
	public void delete(Long idCategory) { 
		repository.deleteById(idCategory);
	}
	
	public Category searchOrFailure(Long idCategory) {
		return repository.findById(idCategory)
				.orElseThrow(() -> new NotFoundCategoryException(idCategory));
	}
	
}
