package io.github.kkzamai.artsocial.domain.repository;

import javax.enterprise.context.ApplicationScoped;

import io.github.kkzamai.artsocial.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User>{
}
