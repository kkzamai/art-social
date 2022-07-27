package io.github.kkzamai.artsocial.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import io.github.kkzamai.artsocial.domain.model.Follower;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower>{
	
}
