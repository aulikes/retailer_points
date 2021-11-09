package com.springboot.retailer.app.retailer_points.converters;

import java.util.List;

public interface EntityConverter <U, T> {
	
	T toDto(U entity);

	U toEntity(T dto);
	
	List<T> listEntityToDto(List<U> listEntity);
	
	List<U> listDtoToEntity(List<T> listDto);
}
