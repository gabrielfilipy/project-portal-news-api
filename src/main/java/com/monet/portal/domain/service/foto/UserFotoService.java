package com.monet.portal.domain.service.foto;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monet.portal.domain.exception.NotFoundFotoUserException;
import com.monet.portal.domain.fotos.UserFoto;
import com.monet.portal.domain.model.User;
import com.monet.portal.domain.repository.UserRepository;
import com.monet.portal.domain.service.FotoStorageService;
import com.monet.portal.domain.service.FotoStorageService.NewFoto;;

@Service
public class UserFotoService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public UserFoto save(UserFoto foto, InputStream inputStream) {
		String nameFileExist = null;
		String nameFile = fotoStorageService.generateNameFile(foto.getNameFile());
		Optional<UserFoto> fotoOld = userRepository.findFotoById(foto.getUser().getIdUser());
		if (fotoOld.isPresent())  {
			nameFileExist = fotoOld.get().getNameFile();
			userRepository.remove(fotoOld.get());
		}
		foto.setNameFile(nameFile);
		foto = userRepository.save(foto);
		userRepository.flush();
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
	
	public UserFoto searchOrFailure(Long idUser) {
		return userRepository.findFotoById(idUser)
				.orElseThrow(() -> new NotFoundFotoUserException(idUser));
	}
	
}
