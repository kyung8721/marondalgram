package com.kyung.marondalgram.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyung.marondalgram.user.domain.User;

@Mapper
public interface UserRepository {
	
	public int addUserRepository(User user);
	
	public int emailDuplicateRepository(@Param("email")String email);
	
	public int nickNameDuplicateRepository(@Param("nickName")String nickName);
	
	public User loginRepository(@Param("loginId") String loginId, @Param("password") String password);

}
