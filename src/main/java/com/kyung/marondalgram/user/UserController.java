package com.kyung.marondalgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marondalgram/user")
public class UserController {
	
	@GetMapping("/join-view")
	public String joinView() {
		return "user/join";
	}
}
