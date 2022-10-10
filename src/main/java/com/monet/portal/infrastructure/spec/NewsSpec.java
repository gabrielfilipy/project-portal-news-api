package com.monet.portal.infrastructure.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.monet.portal.domain.model.News;
import com.monet.portal.domain.repository.filter.NewsFilter;

public class NewsSpec {

	public static Specification<News> toUserFilter(NewsFilter filter) { 
		return (root, query, builder) -> {
			var predicates = new ArrayList<>();
			 
			if(filter.getTitle() != null) 
				predicates.add(builder.like(root.get("title"), "%" + filter.getTitle()+ "%"));
			
			if(filter.getDate() != null) 
				predicates.add(builder.like(root.get("date"), "%" + filter.getDate()+ "%"));
			
			if(filter.getActive() != null) 
				predicates.add(builder.equal(root.get("active"), filter.getActive()));
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
	
}
