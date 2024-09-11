package com.kyung.marondalgram.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyung.marondalgram.user.domain.User;
import com.kyung.marondalgram.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/marondalgram/user")
public class UserRestController {
	
	private UserService userService;
	
	// @Autowired
	UserRestController(UserService userService){
		this.userService = userService;
	}
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("email") String email
			, @RequestParam("name") String name
			, @RequestParam("nickName") String nickName
			, @RequestParam("password") String password
			){
		// 객체에 값 추가
		User user = new User();
		user.setEmail(email);
		user.setRealName(name);
		user.setNickName(nickName);
		user.setPassword(password);
		
		int count = userService.addUserService(user);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	@GetMapping("/duplicate-email")
	public Map<String, Boolean> emailDuplicate(@RequestParam("email") String email){
		int count = userService.emailDuplicateService(email);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		if(count >= 1) {
			resultMap.put("isDuplicate", true); // 이메일 중복됨
		} else {
			resultMap.put("isDuplicate", false); // 이메일 중복되지 않음
		}
		
		
		return resultMap;
	}
	
	@GetMapping("/duplicate-nickName")
	public Map<String, Boolean> nickNameDuplicate(@RequestParam("nickName") String nickName){
		int count = userService.nickNameDuplicateService(nickName);
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		if(count >= 1) {
			resultMap.put("isDuplicate", true); // 이메일 중복됨
		} else {
			resultMap.put("isDuplicate", false); // 이메일 중복되지 않음
		}
		
		
		return resultMap;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request){
		User user = userService.loginService(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			// 로그인 성공
			resultMap.put("result", "success");
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userNickName", user.getNickName());
		} else {
			// 로그인 실패
			resultMap.put("resulte", "fail");
		}
		
		return resultMap;
	}
	
	
}
