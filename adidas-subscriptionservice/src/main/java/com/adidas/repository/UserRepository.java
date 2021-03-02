package com.adidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adidas.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByLogin(String login);
}
