package com.example.blog.service;

import java.util.List;

import com.example.blog.po.Comment;

public interface CommentService {

	List<Comment> listCommentByBlogId(Long blogId);
	
	Comment savaComment(Comment comment);
}
