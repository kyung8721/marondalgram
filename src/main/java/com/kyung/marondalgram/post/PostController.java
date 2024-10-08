package com.kyung.marondalgram.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyung.marondalgram.post.dto.PostDto;
import com.kyung.marondalgram.post.service.PostService;
import com.kyung.marondalgram.user.dto.UserDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/marondalgram/post")
public class PostController {
	
	// postService 객체 생성
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/create/image-view")
	public String imageView() {
		return "post/createImage";
	}
	
	@GetMapping("/create/image-check-view")
	public String imageCheckView(@RequestParam("imagePath") String imagePath, Model model) {
		model.addAttribute("imagePath", imagePath);
		return "post/createImageCheck";
	}
	
	@GetMapping("/create/text-view")
	public String textView(@RequestParam("imagePath") String imagePath, Model model, HttpSession session) {
		model.addAttribute("imagePath", imagePath);
		
		int userId = (Integer)session.getAttribute("userId");
		
		// 작성 중인 사용자 정보 불러오기
		UserDto userDto = postService.getUserData(userId);
		
		// 사용자 정보 저장
		model.addAttribute("user", userDto);
		
		return "post/createText";
	}
	
	@GetMapping("/timeline-view")
	public String timelineView(HttpSession session, Model model) {
		int userId = (Integer)session.getAttribute("userId");
		
		// 작성 중인 사용자 정보 불러오기
		UserDto userDto = postService.getUserData(userId);
		
		// 사용자 정보 저장
		model.addAttribute("user", userDto);
		
		// 게시글 리스트 가져오기
		List<PostDto> cardList = postService.timelinePostList(userId);
		
		model.addAttribute("cardList", cardList);
		return "post/timeline";
	}
}
