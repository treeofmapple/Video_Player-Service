package com.tom.service.storage.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "user_id", nullable = false)
	private UUID userId;
	
	@Column(name = "tag_name", nullable = false)
	private String tagName;
	
	@Column(name = "quantity")
	private int quantity;
	
    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
	private Video video;
	
}
