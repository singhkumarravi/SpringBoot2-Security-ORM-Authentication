package com.olive.repositiory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olive.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{
      
	public Optional<User> findByEmail(String email);
}
