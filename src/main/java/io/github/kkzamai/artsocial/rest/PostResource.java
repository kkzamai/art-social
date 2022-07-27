package io.github.kkzamai.artsocial.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.kkzamai.artsocial.domain.model.Post;
import io.github.kkzamai.artsocial.domain.model.User;
import io.github.kkzamai.artsocial.domain.repository.PostRepository;
import io.github.kkzamai.artsocial.domain.repository.UserRepository;
import io.github.kkzamai.artsocial.rest.dto.CreatePostRequest;
import io.github.kkzamai.artsocial.rest.dto.PostResponse;
import io.quarkus.panache.common.Sort;

@Path("/users/{userid}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

	UserRepository userRepository;
	PostRepository repository;

	@Inject
	public PostResource(UserRepository userRepository, PostRepository repository){
		this.userRepository = userRepository;
		this.repository = repository;
	}

	@POST
	@Transactional
	public Response savePost(
		@PathParam("userid") Long userId, CreatePostRequest request){

		User user = userRepository.findById(userId);
		if(user == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		Post post = new Post();
		post.setText(request.getText());
		post.setUser(user);

		repository.persist(post);

		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	public Response listPosts( @PathParam("userid") Long userId ){
		
		User user = userRepository.findById(userId);
		if(user == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		var query = repository.find("user", Sort.by("dateTime",Sort.Direction.Descending),user);
		var list = query.list();

		var postResponseList = list.stream()
			//.map(post -> PostResponse.fromEntity(post))
			.map(PostResponse::fromEntity)
			.collect(Collectors.toList());
		
		return Response.ok(postResponseList).build();
	}
}
