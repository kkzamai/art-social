package io.github.kkzamai.artsocial.domain.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import io.github.kkzamai.artsocial.domain.model.Follower;
import io.github.kkzamai.artsocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower>{

	public boolean follows(User follower, User user){

		//Map<String, Object> params = new HashMap<>();
		//params.put("follower", follower);
		//params.put("user",user);

		var params = Parameters.with("follower", follower).and("user", user).map();

		PanacheQuery<Follower> query = find("follower =:follower and user =:user", params);
		Optional<Follower> result = query.firstResultOptional();

		return result.isPresent();
	}
	
}
