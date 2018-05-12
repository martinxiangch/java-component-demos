package com.martin.component.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.martin.component.model.User;



public interface UserRepository extends MongoRepository<User, Integer> {

}
