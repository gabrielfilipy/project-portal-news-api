package com.monet.portal.controller.fotos;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.monet.portal.assembler.foto.UserFotoModelAssembler;
import com.monet.portal.domain.fotos.UserFoto;
import com.monet.portal.domain.input.UserFotoInput;
import com.monet.portal.domain.model.User;
import com.monet.portal.domain.service.FotoStorageService;
import com.monet.portal.domain.service.UserService;
import com.monet.portal.domain.service.FotoStorageService.FotoRecovered;
import com.monet.portal.domain.service.foto.UserFotoService;
import com.monet.portal.model.UserFotoDTO;

@RestController
@RequestMapping(path = "/user/{idUser}/foto", 
		produces = MediaType.APPLICATION_JSON_VALUE)
public class UserFotoController {

	@Autowired 
	private UserFotoModelAssembler fotoModelAssembler;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserFotoService userFotoService;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public UserFotoDTO update(@PathVariable Long idUser, @RequestPart MultipartFile file, @Valid UserFotoInput userFotoInput) throws IOException {
		
		User user = userService.searchOrFailure(idUser);
		
		MultipartFile multipartFile = userFotoInput.getFile();
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());
		
		UserFoto userFoto = new UserFoto();
		userFoto.setUser(user);
		userFoto.setDescription(userFotoInput.getDescription());
		userFoto.setContentType(file.getContentType());
		userFoto.setSize(metadata.getContentLength());
		userFoto.setNameFile(file.getOriginalFilename());
		UserFoto fotoSave = userFotoService.save(userFoto, multipartFile.getInputStream());
		
		return fotoModelAssembler.toModel(fotoSave);
	}
	
	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<?> recover(@PathVariable Long idUser, @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try{
			UserFoto userFoto = userFotoService.searchOrFailure(idUser); MediaType mediaTypeFoto = MediaType.parseMediaType(userFoto.getContentType()); 
			List<MediaType> mediaTypeAceitas = MediaType.parseMediaTypes(acceptHeader);
			isCompatible(mediaTypeFoto, mediaTypeAceitas);
			FotoRecovered fotoRecovered = fotoStorageService.recovered(userFoto.getNameFile());
			if(fotoRecovered.isUrl()){
				return ResponseEntity.status(org.springframework.http.HttpStatus.FOUND)
						.header(HttpHeaders.LOCATION, fotoRecovered.getUrl())
						.build();
			} else {
				return ResponseEntity.ok()
						.contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecovered.getInputStream()));
			}
		} catch(Exception e){
			return ResponseEntity.notFound().build();
		}
	}
	
	private void isCompatible(MediaType mediaTypeFoto, 
			List<MediaType> mediaTypeAceitas) throws HttpMediaTypeNotAcceptableException { 
		boolean compatible = mediaTypeAceitas.stream()
				//Validação: se pelo menos um for verdadeiro JPGE ou PNG no caso.
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));
		
		if(!compatible){ 
			throw new HttpMediaTypeNotAcceptableException(mediaTypeAceitas);
		}
	}
	
}
