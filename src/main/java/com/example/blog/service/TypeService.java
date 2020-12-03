package com.example.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.blog.po.Type;

public interface TypeService {
	
	Type saveType(Type type);
	
	Type getType(Long id);
	
	Type getTypeByName(String name);
	
	Page<Type> listType(Pageable Pageable);
	
	List<Type> listType();
	
	Type updateType(Long id, Type type);
	
	void deleteType(Long id);

}
