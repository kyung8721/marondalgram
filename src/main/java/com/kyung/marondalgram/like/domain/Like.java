package com.kyung.marondalgram.like.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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

@Entity
@Table(name = "`like`")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Like {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="postId")
	private int postId;
	
	@Column(name="createdAt")
	@CreationTimestamp
	private LocalDateTime createdAt;
}
