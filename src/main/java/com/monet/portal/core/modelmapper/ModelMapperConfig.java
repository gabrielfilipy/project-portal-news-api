package com.monet.portal.core.modelmapper;

import org.apache.tomcat.util.http.fileupload.MultipartStream.ItemInputStream;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
	    ModelMapper modelMapper = new ModelMapper();
	    return modelMapper;
	}
	
}
