package com.monet.portal.infrastructure.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("portal.storage")
public class StorageProperties {

	private S3 s3 = new S3();
	
	@Getter
	@Setter
	public  class S3 {
		private String accessKeyId;
		private String secretAccess;
		private String bucket;
		private Regions region;
		private String diretorio;
	}
	
}
