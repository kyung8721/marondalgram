package com.kyung.marondalgram.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;


public class FileManager {
	
	// 기본 저장 경로 상수로 저장
	public static final String FILE_UPLOAD_PATH = "C:\\Users\\USER\\Desktop\\JAVA\\springProject\\upload\\marondalgram";
	
	// 파일 저장
	public static String saveFile(int userId, MultipartFile imageFile) {
		if(imageFile == null) {
			return null;
		}
		
		// userId_현재시간정보 폴더 > 이미지 파일
		// 폴더 이름 지정
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 전체 경로 지정
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		// 파일 객체 생성
		File directory = new File(directoryPath);
		
		// 앞의 경로를 기반으로 디렉토리 생성
		if(!directory.mkdir()) {
			// 폴더 생성 실패 시
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + imageFile.getOriginalFilename();
		
		// 실제 파일
		try {
			byte[] bytes = imageFile.getBytes();
			Path path = Paths.get(filePath);
			Files.write(path, bytes); // 경로, 실제 데이터
		} catch (IOException e) {
			// 파일 저장 실패
			return null;
		}
		
		// 저장된 파일을 접근할 URL Path 만들기
		return "/images" + directoryName + "/" + imageFile.getOriginalFilename();
	}
}
