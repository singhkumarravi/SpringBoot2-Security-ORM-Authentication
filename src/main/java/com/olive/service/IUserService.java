package com.olive.service;

import java.util.Optional;

import com.olive.model.User;

public interface IUserService {
	
	public Integer saveUser(User user);
	public Optional<User> findByEmail(String email);

}
