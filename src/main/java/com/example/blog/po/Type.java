package com.example.blog.po;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.google.gson.Gson;

@Entity
@Table(name = "t_type")
public class Type {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "分類名稱不能為空")
	private String name;

	@OneToMany(mappedBy = "type")
	private List<Blog> blogs = new ArrayList<>();
	
	public Type() {
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

	@Override
	public String toString() {
		return "" + this.getClass().getSimpleName() + new Gson().toJson(this);
	}

}
