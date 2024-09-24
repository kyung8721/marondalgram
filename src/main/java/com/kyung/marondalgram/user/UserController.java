package com.kyung.marondalgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyung.marondalgram.post.service.PostService;
import com.kyung.marondalgram.user.dto.UserDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/marondalgram/user")
public class UserController {
	
	private PostService postService;
	public UserController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/join-view")
	public String joinView() {
		return "user/join";
	}
	
	@GetMapping("/login-view")
	public String loginView() {
		return "user/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		// session 내의 내용을 삭제
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userNickName");
		
		return "redirect:/marondalgram/user/login-view";
	}
	
	@GetMapping("/profile-view")
	public String profileView(HttpSession session, Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		// 작성 중인 사용자 정보 불러오기
		UserDto userDto = postService.getUserData(userId);
		
		// 사용자 정보 저장
		model.addAttribute("user", userDto);
		return "user/profile";
	}
}
