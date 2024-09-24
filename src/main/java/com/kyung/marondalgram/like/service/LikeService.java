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
		
		Like like = likeRepository.findByUserIdAndPostId(userId, postId);
		
		int likecount= likeRepository.countByUserIdAndPostId(userId, postId);
		
		if(likecount > 0) {
			likeRepository.delete(like);
			return true;
		}else {
			return false;
		}
	}
	
	// 좋아요 여부 조회
	public boolean likeCheck(int userId, int postId){
		int like = likeRepository.countByUserIdAndPostId(userId, postId);
		if(like > 0) {
			// 좋아요 했음
			return true;
		}else{
			// 좋아요 안 함
			return false;
		}
		
	}
	
	// 좋아요 갯수 조회
	public int likeCount(int postId) {
		return likeRepository.countByPostId(postId);
	}
	
	// 한 포스트 내 좋아요 전부 삭제
	public boolean likeDeletePostAll(int postId) {
		if(likeCount(postId) > 0) {
			likeRepository.deleteByPostId(postId);
			return true;
		} else {
			return false;
		}
	}
}
