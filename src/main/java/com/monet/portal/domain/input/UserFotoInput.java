package com.monet.portal.domain.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.monet.portal.core.validate.FileContentType;
import com.monet.portal.core.validate.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFotoInput {

	@NotNull
	@FileSize(max = "500KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile file;
	
	@NotBlank
	private String description; 
	
}
