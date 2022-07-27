package io.github.kkzamai.artsocial.rest.dto;

import java.time.LocalDateTime;

import io.github.kkzamai.artsocial.domain.model.Post;
import lombok.Data;
import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain.Step.Resolution;

@Data
public class PostResponse {
	
	private String text;
	private LocalDateTime dateTime;

	public static PostResponse fromEntity(Post post){
		
		var response = new PostResponse();
		response.setText(post.getText());
		response.setDateTime(post.getDateTime());
		return response;
	}
}
