package com.kyung.marondalgram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kyung.marondalgram.common.FileManager;
import com.kyung.marondalgram.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	// 이미지 url Path 생성 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**")
		.addResourceLocations("file:///" + FileManager.FILE_UPLOAD_PATH + "/"); // Mac이나 리눅스는 //, 윈도우는 ///
	}
	
	// 인터셉트 설정
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		PermissionInterceptor interceptor = new PermissionInterceptor();
		
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**") // 전체 url이 이 인터셉터를 거치도록 설정
		.excludePathPatterns("/static/**", "/images/**", "/marondalgram/user/logout"); // 인터셉터를 거치지 않을 일부 url 설정
	}
	
	
}
