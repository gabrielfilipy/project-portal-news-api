package com.monet.portal.domain.service.foto;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monet.portal.domain.exception.NotFoundFotoUserException;
import com.monet.portal.domain.fotos.NewsFoto;
import com.monet.portal.domain.fotos.UserFoto;
import com.monet.portal.domain.repository.NewsRepository;
import com.monet.portal.domain.service.FotoStorageService;
import com.monet.portal.domain.service.FotoStorageService.NewFoto;

@Service
public class NewsFotoService {

	@Autowired 
	private NewsRepository newsRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public NewsFoto save(NewsFoto foto, InputStream inputStream) {
		String nameFileExist = null;
		String nameFile = fotoStorageService.generateNameFile(foto.getNameFile());
		Optional<NewsFoto> fotoOld = newsRepository.findFotoById(foto.getNews().getIdNews());
		if (fotoOld.isPresent())  {
			nameFileExist = fotoOld.get().getNameFile();
			newsRepository.remove(fotoOld.get()); 
		}
		foto.setNameFile(nameFile);
		foto = newsRepository.save(foto);
		newsRepository.flush();
		NewFoto newFoto = NewFoto.builder()
				.nameFile(nameFile)
				.contenType(foto.getContentType())
				.inputStream(inputStream)
				.build(); 
		//Call service S3FotoStorageService.
		if (nameFileExist != null)
		fotoStorageService.remove(fotoOld.get().getNameFile());
		
		fotoStorageService.store(newFoto);
		return foto;
	}
	
	public NewsFoto searchOrFailure(Long idNews) {
		return newsRepository.findFotoById(idNews)
				.orElseThrow(() -> new NotFoundFotoUserException(idNews));
	}
	
}
