package com.kyung.marondalgram.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyung.marondalgram.like.domain.Like;
import com.kyung.marondalgram.like.service.LikeService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/marondalgram/post/like")
public class LikeRestController {
	
	//LikeService 객체 생성
	private LikeService likeService;
	public LikeRestController(LikeService likeService) {
		this.likeService = likeService;
	}
	
	// 좋아요 추가
	@PostMapping("/addition")
	public Map<String, String> likeAddition(
			HttpSession session
			, @RequestParam("postId") int postId){
		int userId = (Integer)session.getAttribute("userId");
		
		Like like = likeService.likeAdditionService(userId, postId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(like != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 좋아요 삭제
	@DeleteMapping("/delete")
	public Map<String, String> likeDelete(
			HttpSession session
			, @RequestParam("postId") int postId){
		
		int userId = (Integer)session.getAttribute("userId");
		
		boolean likeDelete = likeService.likeDeleteService(userId, postId);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(likeDelete) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	
}
