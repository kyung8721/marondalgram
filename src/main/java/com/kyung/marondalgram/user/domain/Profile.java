package com.kyung.marondalgram.user.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	private String introduce;
	
	@Column(name="profileImagePath")
	private String profileImagePath;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
