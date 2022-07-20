package io.github.kkzamai.artsocial.rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.kkzamai.artsocial.model.User;
import io.github.kkzamai.artsocial.repository.UserRepository;
import io.github.kkzamai.artsocial.rest.dto.CreateUserRequest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	UserRepository repository;

	@Inject
	public UserResource(UserRepository repository){
		this.repository = repository;
	}

	@POST
	@Transactional
	public Response createUser(CreateUserRequest userRequest){

		User user = new User();
		user.setAge(userRequest.getAge());
		user.setName(userRequest.getName());

		//user.persist();
		repository.persist(user);

		return Response.ok(user).build();
	}

	@GET
	public Response listAllUsers(){
		//PanacheQuery<User> query = User.findAll();
		PanacheQuery<User> query = repository.findAll();
		return Response.ok(query.list()).build();
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Response deleteUser(@PathParam("id") Long id){
		//User user = User.findById(id);
		User user = repository.findById(id);

		if(user != null){
			//user.delete();
			repository.delete(user);
			return Response.ok().build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
		//User user = User.findById(id);
		User user = repository.findById(id);

		if(user != null){
			user.setName(userData.getName());
			user.setAge(userData.getAge());			
			return Response.ok().build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
