package com.kyung.marondalgram.post.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kyung.marondalgram.common.FileManager;
import com.kyung.marondalgram.post.domain.Post;
import com.kyung.marondalgram.post.repository.PostRepository;
import com.kyung.marondalgram.user.dto.UserDto;
import com.kyung.marondalgram.user.service.UserService;

@Service
public class PostService {
	
	// repository 객체 생성
	private PostRepository postRepository;
	private UserService userService;
	
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	// 게시글 저장
	public Post addPost(int userId, String contents, String imagePath, int musicId) {
		
		
		Post post = Post.builder()
				.userId(userId)
				.contents(contents)
				.imagePath(imagePath)
				.musicId(musicId)
				.build();
		
		Post result = postRepository.save(post);
		
		return result;
		
	}
	
	// 파일 저장
	public String imageSaveService(int userId, MultipartFile imageFile) {
		String urlPath = FileManager.saveFile(userId, imageFile);
		return urlPath;
	}
	
	// 작성 중인 사용자 정보 불러오기
	public UserDto getUserData(int userId) {
		return userService.userData(userId);

	}
	
	
}
