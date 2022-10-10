package com.monet.portal.domain.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_news")
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNews;
	private String title;
	private String content;
	private String photoNews;
	private OffsetDateTime date;
	private Long order_id;
	private Boolean active;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "code_category")
	private Category category;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "code_user")
	private User user;
	
}
