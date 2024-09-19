package com.kyung.marondalgram.post.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kyung.marondalgram.common.FileManager;
import com.kyung.marondalgram.post.domain.Post;
import com.kyung.marondalgram.post.repository.PostRepository;

@Service
public class PostService {
	
	// repository 객체 생성
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public Post addPost(int userId, String contents, MultipartFile imageFile, int musicId) {
		
		String urlPath = FileManager.saveFile(userId, imageFile);
		
		Post post = Post.builder()
				.userId(userId)
				.contents(contents)
				.imagePath(urlPath)
				.musicId(musicId)
				.build();
		
		Post result = postRepository.save(post);
		
		return result;
		
	}
}
