package com.example.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.blog.NotFoundException;
import com.example.blog.dao.TypeRepository;
import com.example.blog.po.Type;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeRepository typeRepository;

	@Transactional
	@Override
	public Type saveType(Type type) {
		return typeRepository.save(type);
	}

	@Transactional
	@Override
	public Type getType(Long id) {
		return typeRepository.findById(id).orElse(null);
	}
	
	@Override
	public Type getTypeByName(String name) {
		return typeRepository.findByName(name);
	}

	@Transactional
	@Override
	// Page => 分頁查詢
	public Page<Type> listType(Pageable pageable) {
		return typeRepository.findAll(pageable);
	}
	
	@Override
	public List<Type> listType() {
		return typeRepository.findAll();
	}

	@Transactional
	@Override
	public Type updateType(Long id, Type type) {
		Type tempType = typeRepository.findById(id).orElseThrow(() -> new NotFoundException("不存在該類型"));
		BeanUtils.copyProperties(type, tempType);
		return typeRepository.save(tempType);
	}

	@Transactional
	@Override
	public void deleteType(Long id) {
		typeRepository.deleteById(id);

	}
}
