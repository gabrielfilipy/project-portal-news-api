package com.monet.portal.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	FotoRecovered recovered(String nomeArquivo);
	
	void store(NewFoto newFoto);

	void remove(String nameFile);
	
	default String generateNameFile(String nameOriginal) {
		return UUID.randomUUID().toString() + "-" + nameOriginal;
	}
	
	default void toReplace (String nameFile, NewFoto newFile) {
		this.store(newFile);
		if (nameFile != null) {
			this.remove(nameFile);
		}
	}
	
	@Builder
	@Getter
	class NewFoto {
		private String nameFile;
		private String contenType;
		private InputStream inputStream;
	}
	
	@Builder
	@Getter
	public class FotoRecovered {
		private InputStream inputStream;
		private String url;
		
		public boolean isUrl() {
			return url != null;
		}
		
		public boolean temInputStream() {
			return inputStream != null;
		}
	}
	
}
