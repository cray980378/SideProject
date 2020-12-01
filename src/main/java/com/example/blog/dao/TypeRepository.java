package com.example.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.po.Type;

public interface TypeRepository extends JpaRepository<Type, Long>{

	Type findByName(String name);
	
}
