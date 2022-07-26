package io.github.kkzamai.artsocial.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "USERS")
@Data
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name= "age")
	private Integer age;
}
