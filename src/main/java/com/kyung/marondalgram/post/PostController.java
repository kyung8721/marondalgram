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
	
	@GetMapping("/create/image-check-view")
	public String imageCheckView() {
		return "post/createImageCheck";
	}
	
	@GetMapping("/create/text-view")
	public String textView() {
		return "post/createText";
	}
}
