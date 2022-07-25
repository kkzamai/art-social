package io.github.kkzamai.artsocial.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "POSTS")
@Data
public class Post {

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "post_text")
	private String text;

	@Column(name= "date_time")
	private LocalDateTime dateTime;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;
	
}
