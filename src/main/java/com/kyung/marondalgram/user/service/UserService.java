package com.kyung.marondalgram.user.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kyung.marondalgram.common.HashingEncoder;
import com.kyung.marondalgram.common.MD5HashingEncoder;
import com.kyung.marondalgram.user.domain.User;
import com.kyung.marondalgram.user.repository.UserRepository;

@Service
public class UserService {
	
	// IoC : 제어의 역전(객체를 만드는 사람이 관리하지 않고 프레임워크에 객체 관리를 맡겼으므로)
	// DI : 의존성 주입(Dependency Injection)
	// 의존하는 객체를 주입하는 것
	// 스프링에서 의존성을 만드는 객체를 만드는 것을 스프링에 맡기는 것
	private UserRepository userRepository;
	private HashingEncoder encoder; // HashingEncoder를 상속 받는 놈들 모두를 객체로 만들어 사용할 수 있게 함 -> 클래스에 @Primary가 붙어 있는 것으로 사용됨
	
	
	public UserService(UserRepository userRepository, @Qualifier("sha256Hashing") HashingEncoder encoder) {
		//@Primary를 안 쓸 경우 @Qualifier("sha256Hashing")처럼 주입하는 곳에서 정해줄 수 있음
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	
	
	public int addUserService(User user) {
		// @Service, @Controller 같은 것들에 의해 spring bean에 등록이 됨
		HashingEncoder encoder = new MD5HashingEncoder();
		
		String encryptPassword = encoder.encode(user.getPassword());
		user.setPassword(encryptPassword);
		
		return userRepository.addUserRepository(user);
	}
	
	public int emailDuplicateService(String email) {
		return userRepository.emailDuplicateRepository(email);
	}
	
	public int nickNameDuplicateService(String nickName) {
		return userRepository.nickNameDuplicateRepository(nickName);
	}
}
