package com.monet.portal.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.monet.portal.infrastructure.service.storage.StorageProperties;

/*
 * Essa classe é responsável por gerenciar acesso a Amazon S3.
 */

@Configuration
public class AmazonS3Config {

	@Autowired
	private StorageProperties storageProperties;
	
	@Bean
	public AmazonS3 amazonS3 () {
		var credentials = new BasicAWSCredentials(storageProperties.getS3().getAccessKeyId(), 
				storageProperties.getS3().getSecretAccess());
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(storageProperties.getS3().getRegion())
				.build();
	}
	
}
