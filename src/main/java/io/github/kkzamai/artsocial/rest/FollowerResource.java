package io.github.kkzamai.artsocial.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.kkzamai.artsocial.domain.model.Follower;
import io.github.kkzamai.artsocial.domain.repository.FollowerRepository;
import io.github.kkzamai.artsocial.domain.repository.UserRepository;
import io.github.kkzamai.artsocial.rest.dto.FollowerRequest;

@Path("/users/{userid}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {
	
	FollowerRepository repository;
	UserRepository userRepository;
	
	@Inject
	public FollowerResource(FollowerRepository repository, UserRepository userRepository){
		this.repository = repository;
		this.userRepository = userRepository;
	}

	@PUT
	public Response followerUser(
		@PathParam("userId") Long userId, FollowerRequest request){

		var user = userRepository.findById(userId);
		if(user == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		var follower = userRepository.findById(request.getFollowerId());

		var entity = new Follower();
		entity.setUser(user);
		entity.setFollower(follower);

		repository.persist(entity);

		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
