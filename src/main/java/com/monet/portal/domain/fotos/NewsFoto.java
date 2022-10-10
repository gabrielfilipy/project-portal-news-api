package com.monet.portal.domain.fotos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.monet.portal.domain.model.News;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_foto_news")
public class NewsFoto {

	@Id
	@Column(name = "user_id_news")
	private Long idNews;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private News news;
	
	private String nameFile;
	private String description;
	private String contentType;
	private Long size;
	
}
