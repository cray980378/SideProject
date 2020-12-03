package com.example.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.blog.po.Tag;

public interface TagService {
	
	Tag saveTag(Tag tag);
	
	Tag getTag(Long id);
	
	Tag getTagByName(String name);
	
	Page<Tag> listTag(Pageable Pageable);
	
	List<Tag> listTag();
	
	List<Tag> listTag(String ids);
	
	Tag updateTag(Long id, Tag tag);
	
	void deleteTag(Long id);

}
