package com.example.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.dao.UserRepository;
import com.example.blog.po.User;
import com.example.blog.util.MD5Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository ;
	
	@Override
	public User checkUser(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, MD5Utils.encode(password)) ;
		return user;
	}

}
