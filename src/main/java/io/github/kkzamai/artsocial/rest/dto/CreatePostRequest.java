package io.github.kkzamai.artsocial.rest.dto;

import lombok.Data;

@Data
public class CreatePostRequest {
	//@NotBlank(message="Text is required.")
	private String text;
}
