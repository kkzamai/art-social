package io.github.kkzamai.artsocial.rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
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

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FollowerResource {
	
	private FollowerRepository repository;
	private UserRepository userRepository;
	
	@Inject
	public FollowerResource(
			FollowerRepository repository, UserRepository userRepository){
		this.repository = repository;
		this.userRepository = userRepository;
	}

	@PUT
	@Transactional
	public Response followerUser(
		@PathParam("userId") Long userId, FollowerRequest request){

		if(userId.equals(request.getFollowerId())){
			return Response.status(Response.Status.CONFLICT).entity("You can't follow yourself.").build();
		}

		var user = userRepository.findById(userId);
		if(user == null){
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		var follower = userRepository.findById(request.getFollowerId());

		boolean follows = repository.follows(follower, user);

		if(!follows){
			var entity = new Follower();
			entity.setUser(user);
			entity.setFollower(follower);

			repository.persist(entity);
		}

		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
