package com.monet.portal.assembler.foto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monet.portal.domain.fotos.UserFoto;
import com.monet.portal.model.UserFotoDTO;

@Component
public class UserFotoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserFotoDTO toModel(UserFoto foto) {
		return modelMapper.map(foto, UserFotoDTO.class);
	}
	
}
