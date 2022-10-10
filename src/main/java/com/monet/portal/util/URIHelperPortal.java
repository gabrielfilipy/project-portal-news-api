package com.monet.portal.util;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

/*
 * Essa classe ajuda
 * a idenficar as URI
 * dos recursos
 */

@UtilityClass
public class URIHelperPortal {

	public static void addURIInHeader(Long idResource) {
		//Pega da requisição e monta a URL
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
			.path("/{id}")
			.buildAndExpand(idResource).toUri();
		
		HttpServletResponse response = ((ServletRequestAttributes) 
				RequestContextHolder.getRequestAttributes()).getResponse();
		response.setHeader(HttpHeaders.LOCATION, uri.toString());
	}
	
}
