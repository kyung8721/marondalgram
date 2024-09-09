package com.kyung.marondalgram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

// Spring bean에 등록하기 위한 annotaion
@Component("mdHashing")
// @Primary // 의존성 우선순위 부여
public class MD5HashingEncoder implements HashingEncoder{
	
	// 객체 생성 없이 호출
	public String encode(String message) {
		
		String result = "";
		
		try {
			
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes(); // 문자열을 바이트 단위로 변경
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			// byte 배열의 값을 16진수 문자열 형태로 변환
			for(int i = 0; i < digest.length; i++) {
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			return null;
		}
		
		return result;
		
	}
}
