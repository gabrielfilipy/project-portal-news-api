package com.monet.portal.domain.repository.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsFilter {

	private String title;
	private LocalDate date;
	private Boolean active;
	
}
