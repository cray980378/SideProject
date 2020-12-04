package com.example.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.blog.NotFoundException;
import com.example.blog.dao.BlogRepository;
import com.example.blog.po.Blog;
import com.example.blog.po.Type;
import com.example.blog.util.MyBeanUtils;
import com.example.blog.vo.BlogQuery;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;

	@Override
	public Blog getBlog(Long id) {
		return blogRepository.findById(id).orElse(null);
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery) {

		return blogRepository.findAll(new Specification<Blog>() {

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (blogQuery.getTitle() != null && !blogQuery.getTitle().trim().equals("")) {
					predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blogQuery.getTitle() + "%"));
				}

				if (blogQuery.getTypeId() != null) {
					predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blogQuery.getTypeId()));
				}

				if (blogQuery.isRecommend()) {
					predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blogQuery.isRecommend()));
				}

				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
	}

	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {

		if (blog.getId() == null) {
			blog.setCreateTime(new Date());
			blog.setUpdateTime(new Date());
			blog.setViews(0);
		} else {
			blog.setUpdateTime(new Date());
		}
		return blogRepository.save(blog);
	}

	@Transactional
	@Override
	public Blog updateBlog(Long id, Blog blog) {
		Blog b = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("此文章不存在"));
		BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullPropertyNames(blog));
		b.setUpdateTime(new Date());
		return blogRepository.save(b);
	}

	@Transactional
	@Override
	public void deleteBlog(Long id) {
		blogRepository.deleteById(id);

	}

}
