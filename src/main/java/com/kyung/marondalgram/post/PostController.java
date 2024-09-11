package com.kyung.marondalgram.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marondalgram/post")
public class PostController {
	
	@GetMapping("/create/image-view")
	public String imageView() {
		return "post/createImage";
	}
}
