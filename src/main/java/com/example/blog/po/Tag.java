package com.example.blog.po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.google.gson.Gson;

@Entity
@Table(name = "t_tag")
public class Tag {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "標籤名稱不能為空")
	private String name;

	@ManyToMany(mappedBy = "tags")
	private List<Blog> blogs = new ArrayList<>();

	public Tag() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}
}
