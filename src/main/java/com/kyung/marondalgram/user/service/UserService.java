package com.kyung.marondalgram.user.service;

import org.springframework.stereotype.Service;

import com.kyung.marondalgram.common.MD5HashingEncoder;
import com.kyung.marondalgram.user.domain.User;
import com.kyung.marondalgram.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	public int addUserService(User user) {
		String encryptPassword = MD5HashingEncoder.encode(user.getPassword());
		user.setPassword(encryptPassword);
		
		return userRepository.addUserRepository(user);
	}
}
