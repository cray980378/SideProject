package com.example.blog.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.blog.po.Blog;
import com.example.blog.po.User;
import com.example.blog.service.BlogService;
import com.example.blog.service.TagService;
import com.example.blog.service.TypeService;
import com.example.blog.vo.BlogQuery;

@Controller
@RequestMapping("admin")
public class BlogController {

	private static final String WEB_PATH_BLOG_INPUT = "admin/blogs-input";
	private static final String WEB_PATH_BLOG_LIST = "admin/blogs";
	private static final String REDIRECT_WEB_PATH_BLOG_LIST = "redirect:/admin/blogs";

	@Autowired
	private BlogService blogService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;

	@GetMapping("/blogs")
	public String blogs(
			@PageableDefault(size = 2, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
			BlogQuery blogQuery, Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
		return WEB_PATH_BLOG_LIST;
	}

	@PostMapping("/blogs/search")
	public String search(
			@PageableDefault(size = 2, sort = { "updateTime" }, direction = Sort.Direction.DESC) Pageable pageable,
			BlogQuery blogQuery, Model model) {
		model.addAttribute("page", blogService.listBlog(pageable, blogQuery));
		return WEB_PATH_BLOG_LIST + " :: blogList";
	}

	@GetMapping("/blogs/input")
	public String input(Model model) {
		setTypeAndTag(model);
		model.addAttribute("blog", new Blog());
		return WEB_PATH_BLOG_INPUT;
	}

	private void setTypeAndTag(Model model) {
		model.addAttribute("tags", tagService.listTag());
		model.addAttribute("types", typeService.listType());
	}

	@GetMapping("/blogs/{id}/input")
	public String editInput(@PathVariable Long id, Model model) {
		setTypeAndTag(model);
		Blog blog = blogService.getBlog(id);
		blog.init();
		model.addAttribute("blog", blog);
		return WEB_PATH_BLOG_INPUT;
	}

	@PostMapping("/blogs")
	public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {

		blog.setUser((User) session.getAttribute("user"));
		blog.setType(typeService.getType(blog.getType().getId()));
		blog.setTags(tagService.listTag(blog.getTagIds()));

		Blog blogTemp;

		if (blog.getId() == null) {
			blogTemp = blogService.saveBlog(blog);
		} else {
			blogTemp = blogService.updateBlog(blog.getId(), blog) ;
		}
		
		if (blogTemp == null) {
			attributes.addFlashAttribute("message", "操作失敗");
		} else {
			attributes.addFlashAttribute("message", "操作成功");
		}

		return REDIRECT_WEB_PATH_BLOG_LIST;
	}

	@GetMapping("/blogs/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		blogService.deleteBlog(id);
		attributes.addFlashAttribute("message", "刪除成功");
		return REDIRECT_WEB_PATH_BLOG_LIST;
	}

}
