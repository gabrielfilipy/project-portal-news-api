package com.monet.portal.controller;

import java.time.OffsetDateTime;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.monet.portal.assembler.NewsModelAssembler;
import com.monet.portal.domain.model.News;
import com.monet.portal.domain.repository.NewsRepository;
import com.monet.portal.domain.repository.filter.NewsFilter;
import com.monet.portal.domain.service.NewsService;
import com.monet.portal.infrastructure.spec.NewsSpec;
import com.monet.portal.model.NewsDTO;
import com.monet.portal.util.URIHelperPortal;

@RestController
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsRepository repository;
	
	@Autowired
	private NewsService service;
	
	@Autowired
	private NewsModelAssembler assembler;
	
	@Autowired
	private PagedResourcesAssembler<News> pagedDTOAssembler;
	
	@GetMapping
	public PagedModel<NewsDTO> listar(NewsFilter filter, @PageableDefault(size = 10) Pageable pageable) {
		Page<News> newsPage = repository.findAll(NewsSpec.toUserFilter(filter), pageable);
		
		PagedModel<NewsDTO> pagedModelCategory = 
				pagedDTOAssembler.toModel(newsPage, assembler);
		return pagedModelCategory;
	}
	
	@PostMapping()
	public NewsDTO add(@RequestBody @Valid News news) {
		news.setDate(OffsetDateTime.now());
		News newsSave = service.add(news);  
		//View URI in Header/Location.
		URIHelperPortal.addURIInHeader(newsSave.getIdNews());
		return assembler.toModel(newsSave);
	}
	
	@GetMapping("/{idNews}") 
	public NewsDTO returnId(@PathVariable Long idNews) {
		News news = service.searchOrFailure(idNews);
		return assembler.toModel(news);
	}
	
	@PutMapping("/{idNews}")
	public ResponseEntity<?> update(@Valid @RequestBody News news, @PathVariable Long idNews) {	
		News newsSave = service.update(idNews, news);
		if(newsSave != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(newsSave);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{idNews}")
	public ResponseEntity<?> remove(@PathVariable Long idNews) {
		Optional<News> news = repository.findById(idNews);
		if (news.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		else {
			repository.deleteById(idNews);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PutMapping("/{idNews}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateSituationNews(@PathVariable Long idNews, @RequestBody Boolean active) {
		service.updateSituationNews(idNews, active);
	}
	
	@PutMapping("/{idNews}/order")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateOrderNews(@PathVariable Long idNews, @RequestBody Long orderId) {
		News news = repository.findByNewsPositionOrder(idNews, orderId);
		if (news != null)
			service.updateOrderNews(idNews, orderId);
			
		service.updateOrderNews(idNews, orderId);
	}
	
}
