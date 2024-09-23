package com.kyung.marondalgram.like.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyung.marondalgram.like.domain.Like;
import com.kyung.marondalgram.like.repository.LikeRepository;

@Service
public class LikeService {
	// repository 객체 생성
	private LikeRepository likeRepository;
	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
	
	// 좋아요 추가 서비스
	public Like likeAdditionService(int userId, int postId) {
		
		Like like = Like.builder()
					.userId(userId)
					.postId(postId)
					.build();
		
		return likeRepository.save(like);
	}
	
	// 좋아요 삭제 서비스
	public boolean likeDeleteService(int userId, int postId) {
		
		Like like = Like.builder()
				.userId(userId)
				.postId(postId)
				.build();
		
		List<Like> likeList= likeRepository.findByUserIdAndPostId(userId, postId);
		
		if(likeList != null) {
			likeRepository.delete(like);
			return true;
		}else {
			return false;
		}
	}
	
	// 좋아요 조회
	public boolean likeCheck(int userId, int postId){
		List<Like> like = likeRepository.findAllByUserIdAndPostId(userId, postId);
		
		if(like != null) {
			// 좋아요 있음
			return true;
		}else {
			// 좋아요 없음
			return false;
		}
	}
}
