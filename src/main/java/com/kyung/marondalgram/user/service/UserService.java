package com.kyung.marondalgram.user.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kyung.marondalgram.common.HashingEncoder;
import com.kyung.marondalgram.common.MD5HashingEncoder;
import com.kyung.marondalgram.user.domain.Profile;
import com.kyung.marondalgram.user.domain.User;
import com.kyung.marondalgram.user.dto.UserDto;
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
	
	public User loginService(String loginId, String password) {
		HashingEncoder encoder = new MD5HashingEncoder();
		
		String encryptPassword = encoder.encode(password);
		
		return userRepository.loginRepository(loginId, encryptPassword);
	}
	
	// user 정보 불러오기
	public User selectUserById(int id) {
		return userRepository.selectUserById(id);
	}
	
	// user 프로필 정보 불러오기
	public Profile selectUserProfileById(int id) {
		return userRepository.selectUserProfileById(id);
	}
	
	// 사용자 정보 가져오기
	public UserDto userData(int userId) {
		User user = userRepository.selectUserById(userId);
		Profile profile = userRepository.selectUserProfileById(userId);
		
		if(profile == null) {
			// 기본값 지정
			profile = new Profile();
			profile.setProfileImagePath("https://cdn.pixabay.com/photo/2015/11/06/11/43/businessman-1026415_1280.jpg");
			profile.setId(0);
			
		}
		
		UserDto userDto = UserDto.builder()
				.userId(user.getId())
				.profileId(profile.getId())
				.loginId(user.getNickName())
				.profileImagePath(profile.getProfileImagePath())
				.build();
		
		return userDto;
	}
}
