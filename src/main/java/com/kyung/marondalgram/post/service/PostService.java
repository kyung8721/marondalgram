package com.kyung.marondalgram.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kyung.marondalgram.comment.dto.CommentDto;
import com.kyung.marondalgram.comment.service.CommentService;
import com.kyung.marondalgram.common.FileManager;
import com.kyung.marondalgram.like.service.LikeService;
import com.kyung.marondalgram.post.domain.Post;
import com.kyung.marondalgram.post.dto.PostDto;
import com.kyung.marondalgram.post.repository.PostRepository;
import com.kyung.marondalgram.user.dto.UserDto;
import com.kyung.marondalgram.user.service.UserService;

@Service
public class PostService {
	
	// repository 객체 생성
	private PostRepository postRepository;
	private UserService userService;
	private LikeService likeService;
	private CommentService commentService;
	
	public PostService(PostRepository postRepository, UserService userService, LikeService likeService, CommentService commentService) {
		this.postRepository = postRepository;
		this.userService = userService;
		this.likeService = likeService;
		this.commentService = commentService;
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
	
	// 타임라인에 보여질 게시글 불러오기
	public List<PostDto> timelinePostList(int userId){
		// 전체 게시글 리스트 불러오기
		List<Post> postList = postRepository.findAllByOrderByUpdatedAtDesc();
		
		// 게시글 카드 리스트 생성
		List<PostDto> cardList = new ArrayList<>(); 
		
		// 게시글 카드 하나 불러오고 리스트에 저장
		for(Post post : postList) {
			int postUserId = post.getUserId();
			int postId =  post.getId();
			UserDto userDto =  userService.userData(postUserId);
			
			boolean checkLike = likeService.likeCheck(userId, postId);
			int likeCount = likeService.likeCount(postId);
			
			List<CommentDto> commentList = commentService.getCommentList(postId);
			int commentCount = commentService.commentCountService(postId);
			
			PostDto postDto = PostDto.builder()
							.postId(postId)
							.userId(userDto.getUserId())
							.contents(post.getContents())
							.imagePath(post.getImagePath())
							.musicId(post.getMusicId())
							.loginId(userDto.getLoginId())
							.profileImagePath(userDto.getProfileImagePath())
							.like(checkLike)
							.commentList(commentList)
							.commentCount(commentCount)
							.createdAt(post.getCreatedAt())
							.updatedAt(post.getUpdatedAt())
							.likeCount(likeCount)
							.build();
			cardList.add(postDto);
		}
		
		return cardList;
		
	}
	
	// 게시글 삭제
	public boolean postDeleteService(int postId, int userId) {
		
		
		
		// 해당 post 객체 불러오기
		Optional<Post> optionalPost = postRepository.findByIdAndUserId(postId, userId);
		Post post = optionalPost.orElse(null);
		
		// 해당 post 제거
		if(post != null) {
			// 좋아요 삭제
			likeService.likeDeletePostAll(postId);
			
			// 댓글 삭제
			commentService.deleteCommentPostAll(postId);
			
			// 저장된 이미지
			FileManager.deleteImageFile(post.getImagePath());
			// 포스트
			postRepository.delete(post);
			
			return true;
		}else {
			return false;
		}
		
		
	}
	
	// 게시글 수정
	public Post changePost(int postId, String contents, String imagePath, int musicId) {
		// post가 있는지 확인
		Optional<Post> optionalPost = postRepository.findById(postId);
		optionalPost.orElse(null);
		
		if(optionalPost != null) {
			// post가 있으면
			Post updatePost = Post.toBuilder()
							.id(postId)
							.contents(contents)
							.imagePath(imagePath)
							.musicId(musicId)
							.build();
			Post result = postRepository.save(updatePost);
		}
	}
	
}
