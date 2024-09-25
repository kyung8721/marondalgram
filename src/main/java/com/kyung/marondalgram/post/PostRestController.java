package com.kyung.marondalgram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kyung.marondalgram.post.domain.Post;
import com.kyung.marondalgram.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/marondalgram/post")
public class PostRestController {
	
	// PostService 객체
	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	// 게시글 저장 API
	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam("contents") String contents
			, @RequestParam("imagePath") String imagePath
			, @RequestParam("musicId") int musicId
			, HttpSession session){
		
		int userId = (Integer)session.getAttribute("userId");
		
		Post post = postService.addPost(userId, contents, imagePath, musicId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(post != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	// 게시글 사진 저장 API
	@PostMapping("/create/imageSave")
	public Map<String, String> ImageSave(
			@RequestParam(value = "imageFile", required = false) MultipartFile imageFile
			, HttpSession session){
		int userId = (Integer)session.getAttribute("userId");
		
		String urlPath = postService.imageSaveService(userId, imageFile);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(urlPath != null) {
			resultMap.put("result", "success");
			resultMap.put("imagePath", urlPath);
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	// 게시글 삭제 API
	@DeleteMapping("/delete")
	public Map<String, String> postDelete(
			HttpSession session
			, @RequestParam("postId") int postId){
		int userId = (Integer)session.getAttribute("userId");
		
		boolean result = postService.postDeleteService(postId, userId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(result) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
}
