package com.example.blog.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.blog.po.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import com.example.blog.vo.BlogQuery;

@Controller
public class TypeShowController {

	@Autowired
	private TypeService typeService;
	@Autowired
	private BlogService blogService;

	@GetMapping("/types/{id}")
	public String types(
			@PageableDefault(size = 8, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
			@PathVariable Long id, Model model) {
		List<Type> types = typeService.listTypeTop(1000);

		if (id == -1 && !types.isEmpty()) {
			id = types.get(0).getId();
		}
		
		BlogQuery blogQuery = new BlogQuery() ;
		blogQuery.setTypeId(id);
		model.addAttribute("types", types);
		model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
		model.addAttribute("activeTypeId", id);
		

		return "types";
	}
}
