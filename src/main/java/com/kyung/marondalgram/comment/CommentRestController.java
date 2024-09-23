package com.kyung.marondalgram.comment;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyung.marondalgram.comment.domain.Comment;
import com.kyung.marondalgram.comment.service.CommentService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/marondalgram/post/comment")
public class CommentRestController {
	
	private CommentService commentService;
	public CommentRestController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/create")
	public Map<String, String> additionComment(
			HttpSession session
			, @RequestParam("postId") int postId
			, @RequestParam("comment") String comment){
		int userId = (Integer)session.getAttribute("userId");
		
		Comment result = commentService.additionCommentService(userId, postId, comment);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(result != null) {
			resultMap.put("result", "success");
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
