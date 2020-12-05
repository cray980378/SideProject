package com.example.blog.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.po.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

	Tag findByName(String name);
	
	@Query("SELECT t FROM Tag t")
	List<Tag> findTop(Pageable pageable);
	
}
