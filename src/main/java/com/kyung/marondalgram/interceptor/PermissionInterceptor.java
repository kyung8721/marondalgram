package com.kyung.marondalgram.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// 요청된 url을 받아옴
		String uri = request.getRequestURI();
		
		// 로그인이 안된 상태라면 로그인 화면으로 이동
		if(userId == null) {
			// url이 post로 시작된다면
			if(uri.startsWith("/marondalgram/post/")) {
				response.sendRedirect("/marondalgram/user/login-view");
				return false;
			}
		}else {
			// 로그인된 상태라면 로그인 화면에 접근하지 못 하도록 설정
			if(uri.startsWith("/marondalgram/user/")) {
				response.sendRedirect("/marondalgram/post/timeline-view");
				return false;
			}
		}
		
		return true;
	}
}
