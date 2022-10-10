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
import com.monet.portal.assembler.foto.NewsFotoModelAssembler;
import com.monet.portal.domain.fotos.NewsFoto;
import com.monet.portal.domain.input.UserFotoInput;
import com.monet.portal.domain.model.News;
import com.monet.portal.domain.service.FotoStorageService;
import com.monet.portal.domain.service.NewsService;
import com.monet.portal.domain.service.FotoStorageService.FotoRecovered;
import com.monet.portal.domain.service.foto.NewsFotoService;
import com.monet.portal.model.NewsFotoDTO;

@RestController
@RequestMapping(path = "/news/{idNews}/foto", 
		produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsFotoController {

	@Autowired
	private NewsFotoModelAssembler fotoModelAssembler;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NewsFotoService newsFotoService;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public NewsFotoDTO update(@PathVariable Long idNews, @RequestPart MultipartFile file, 
			@Valid UserFotoInput userFotoInput) throws IOException {
		
		News news = newsService.searchOrFailure(idNews);
		
		MultipartFile multipartFile = userFotoInput.getFile();
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(file.getContentType());
		metadata.setContentLength(file.getSize());
		
		NewsFoto newsFoto = new NewsFoto();
		newsFoto.setNews(news);
		newsFoto.setDescription(userFotoInput.getDescription());
		newsFoto.setContentType(file.getContentType());
		newsFoto.setSize(metadata.getContentLength());
		newsFoto.setNameFile(file.getOriginalFilename());
		NewsFoto fotoSave = newsFotoService.save(newsFoto, multipartFile.getInputStream());
		
		return fotoModelAssembler.toModel(fotoSave);
	}
	
	@GetMapping(produces = MediaType.ALL_VALUE)
	public ResponseEntity<?> recover(@PathVariable Long idNews, @RequestHeader(name = "accept") 
		String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try{
			NewsFoto userFoto = newsFotoService.searchOrFailure(idNews); 
			MediaType mediaTypeFoto = MediaType.parseMediaType(userFoto.getContentType()); 
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
