package com.kyung.marondalgram.comment.dto;


import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDto {
	private int id;
	
	private int userId;
	private String loginId;
	
	private int postId;
	
	private String comment;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
