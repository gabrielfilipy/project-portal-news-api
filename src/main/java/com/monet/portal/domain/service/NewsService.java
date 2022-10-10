package com.monet.portal.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monet.portal.domain.exception.NotFoundNewsException;
import com.monet.portal.domain.exception.NotFoundUserException;
import com.monet.portal.domain.model.Category;
import com.monet.portal.domain.model.News;
import com.monet.portal.domain.repository.CategoryRepository;
import com.monet.portal.domain.repository.NewsRepository;

@Service
public class NewsService {

	@Autowired
	private NewsRepository repository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public News update(Long idNews, News news) {
		News newsSave = searchOrFailure(idNews);
		if (newsSave != null) {
			BeanUtils.copyProperties(news, newsSave, "idNews");
			return repository.save(newsSave);
		}
		return null;
	}
	
	@Transactional
	public News add(News news) {
		categoryService.searchOrFailure(news.getCategory().getIdCategory());
		userService.searchOrFailure(news.getUser().getIdUser());
		return repository.save(news);
	}
	
	//partial update implementation
	public void updateSituationNews(Long id, Boolean active) {
		News newsSave = searchOrFailure(id);
		if(newsSave != null){
			newsSave.setActive(active);
			repository.save(newsSave);
		}
	}
	
	public void updateOrderNews(Long idNews, Long orderId) {
		News newsSave = searchOrFailure(idNews);
		if(newsSave != null){
			newsSave.setOrder_id(orderId);
			repository.save(newsSave);
		}
	}
	
	public News searchOrFailure(Long idNews) {
		return repository.findById(idNews)
				.orElseThrow(() -> new NotFoundNewsException(idNews));
	}
	
	public News searchOrderThisNews(Long idNews, Long orderId) {
		return repository.findByNewsPositionOrder(idNews, orderId);
	}
	
}
