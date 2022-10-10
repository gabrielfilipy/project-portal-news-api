package com.monet.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monet.portal.PortalLinks;
import com.monet.portal.model.CategoryDTO;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private PortalLinks portalLinks;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		rootEntryPointModel.add(portalLinks.linkToCategory(""));
		rootEntryPointModel.add(portalLinks.linkToNews(""));
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel
		extends RepresentationModel<CategoryDTO> { }
	
}
