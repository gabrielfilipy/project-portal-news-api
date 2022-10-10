package com.monet.portal.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.monet.portal.PortalLinks;
import com.monet.portal.controller.NewsController;
import com.monet.portal.domain.model.News;
import com.monet.portal.model.NewsDTO;

@Component
public class NewsModelAssembler 
	extends RepresentationModelAssemblerSupport<News, NewsDTO> {

	//HATEOS
	public NewsModelAssembler() {
		super(NewsController.class, NewsDTO.class);
	}

	@Autowired
	private ModelMapper modelMapper;
		
	@Autowired
	private PortalLinks portalLinks;
		
	public NewsDTO toModel(News news) {
		NewsDTO newsDTO = modelMapper.map(news, NewsDTO.class);
		//HATEOS
		newsDTO.add(portalLinks.linkToNews(newsDTO.getIdNews()));
		newsDTO.add(portalLinks.linkToNews(""));
		return newsDTO;
	}
	
}
