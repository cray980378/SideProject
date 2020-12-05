package com.example.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.blog.po.Blog;
import com.example.blog.vo.BlogQuery;

public interface BlogService {

	Blog getBlog(Long id);

	Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);

	Page<Blog> listBlog(Pageable pageable);

	Page<Blog> listBlog(String query, Pageable pageable);

	List<Blog> listRecommendBlogTop(Integer size);
	
	Blog saveBlog(Blog blog);

	Blog updateBlog(Long id, Blog blog);
	
	void deleteBlog(Long id);

}
