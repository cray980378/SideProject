package com.example.blog.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.blog.po.Blog;
import com.example.blog.service.BlogService;

@Controller
public class ArchiveController {

	@Autowired
	private BlogService blogService;

	@GetMapping("/archives")
	public String archives(Model model) {

		Map<String, List<Blog>> map = blogService.archiveBlog();

		int size = 0;
		for (List<Blog> blogs : map.values()) {
			size += blogs.size();
		}

		model.addAttribute("archiveMap", blogService.archiveBlog());
		model.addAttribute("blogCount", size);
		
		return "archives";
	}
}
