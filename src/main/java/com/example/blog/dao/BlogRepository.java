package com.example.blog.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.blog.po.Blog;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog>{

	@Query("SELECT b FROM Blog b WHERE b.recommend = true")
	List<Blog> findTop(Pageable pageable);
	
	@Query("SELECT b FROM Blog b WHERE b.title LIKE ?1 OR b.content LIKE ?1")
	Page<Blog> findByQuery(String query, Pageable pageable);
	
}
