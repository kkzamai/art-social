package io.github.kkzamai.artsocial.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import io.github.kkzamai.artsocial.domain.model.Post;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post>{
	
}
