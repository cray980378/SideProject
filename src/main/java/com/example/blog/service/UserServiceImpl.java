package com.example.blog.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.blog.dao.UserRepository;
import com.example.blog.po.User;
import com.example.blog.util.MD5Utils;
import com.example.blog.vo.DefaultUser;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DefaultUser defaultUser;

	@Override
	public User checkUser(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, MD5Utils.encode(password));
		return user;
	}

	@EventListener(ApplicationReadyEvent.class)
	@Override
	public User saveDefaultUser() {

		if (userRepository.count() < 1) {
			User user = new User();

			BeanUtils.copyProperties(defaultUser, user);

			user.setPassword(MD5Utils.encode(user.getPassword()));
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());

			return userRepository.save(user);
		}

		return null;
	}

}
