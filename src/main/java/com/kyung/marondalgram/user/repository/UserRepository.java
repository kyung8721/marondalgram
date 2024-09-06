package com.kyung.marondalgram.user.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kyung.marondalgram.user.domain.User;

@Mapper
public interface UserRepository {
	
	public int addUserRepository(User user);

}
