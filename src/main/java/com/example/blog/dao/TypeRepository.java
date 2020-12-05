package com.example.blog.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.po.Type;

public interface TypeRepository extends JpaRepository<Type, Long>{

	Type findByName(String name);
	
	@Query("SELECT t FROM Type t")
	List<Type> findTop(Pageable pageable);
	
}
