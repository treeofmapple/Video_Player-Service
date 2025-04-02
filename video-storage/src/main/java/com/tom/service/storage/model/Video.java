package com.tom.service.storage.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Tag> tags;
	
	@Column(name = "duration", nullable = false)
	private float duration;
	
	@Column(name = "video_url")
	private String videoUrl;
	
	@Column(name = "format", nullable = false)
	private String format;
	
	@Column(name = "file_size", nullable = false)
	private float fileSize;
	
	@Column(name = "views")
	private int views;
	
	@Column(name = "date_created", nullable = false)
	private LocalDateTime dateCreated;
	
	@Column(name = "last_access_time")
	private LocalDateTime lastAccessTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "access", nullable = false)
	private Access access;
	
	/*
	 
	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Column(name = "comments")
	private List<UserComments> comments;
	
	*/
	
	@PrePersist
	private void prePersist() {
		this.dateCreated = LocalDateTime.now();	
	}
	
}
