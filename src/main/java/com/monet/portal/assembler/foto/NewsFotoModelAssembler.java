package com.monet.portal.assembler.foto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monet.portal.domain.fotos.NewsFoto;
import com.monet.portal.model.NewsFotoDTO;

@Component
public class NewsFotoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public NewsFotoDTO toModel(NewsFoto foto) {
		return modelMapper.map(foto, NewsFotoDTO.class);
	}
	
}
