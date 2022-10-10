package com.monet.portal.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.monet.portal.domain.service.FotoStorageService;


@Service
public class S3FotoStorageService implements FotoStorageService{

	@Autowired
	AmazonS3 amazonS3;
	
	@Autowired
	StorageProperties storageProperties;
	
	@Override
	public FotoRecovered recovered(String nameFile) {
		String pathFile = getPathFile(nameFile);
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), pathFile);
		return FotoRecovered.builder()
				.url(url.toString()).build();
	}

	@Override
	public void store(NewFoto newFoto) {
		try {
		String pathFile = getPathFile(newFoto.getNameFile());
		var objectMetaData = new ObjectMetadata();
		objectMetaData.setContentType(newFoto.getContenType());
		//putObjectRequest : prepare the requisition
		var putObjectRequest = new PutObjectRequest(
				storageProperties.getS3().getBucket(), 
				pathFile,
				newFoto.getInputStream(), 
				objectMetaData)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		//call requisition
		amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Unable to upload file to Amazon S3", e);
		}
	}

	@Override
	public void remove(String nameFile) {
		try {
			String pathFile = getPathFile(nameFile);
			var deleteObjectRequest = new DeleteObjectRequest(
	                storageProperties.getS3().getBucket(), pathFile);
	        amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new StorageException("Unable to remove file for Amazon S3.", e);
		}
	}
	
	private String getPathFile(String nameFile) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorio(), nameFile);
	}
	
}
