package com.monet.portal.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.monet.portal.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("from Category c where c.description LIKE %:description%")
	Page<Category> collectionToDescription (String description, Pageable pageable);
	
}
