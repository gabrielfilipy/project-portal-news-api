package com.monet.portal.core.jackson;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/*
 * Essa classe tem o objetivo de ser um serializador json do tipo Page.
 * Ou seja, aqui da pra customizar a representação da paginação.
 */

@JsonComponent
public class SerializerJsonPage extends JsonSerializer<Page<?>> {

	@Override
	public void serialize(Page<?> value, JsonGenerator gen, 
			SerializerProvider serializers) throws IOException {
		
		//Começa um objeto
		gen.writeStartObject();
				
		//Meio do objeto
		gen.writeObjectField("content", value.getContent());
		gen.writeNumberField("size", value.getSize());
		gen.writeNumberField("totalElements", value.getTotalElements());
		gen.writeNumberField("totalPages", value.getTotalPages());
		gen.writeNumberField("number", value.getNumber());
				
		//Finaliza um objeto
		gen.writeEndObject();
	}
	
}
