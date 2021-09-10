package com.olive.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.olive.model.User;
import com.olive.repositiory.UserRepo;
import com.olive.service.IUserService;
@Service
public class UserServieImpl implements IUserService ,UserDetailsService{
	@Autowired
	private UserRepo repo;
	@Autowired
	private BCryptPasswordEncoder pwd_encode;

	public Integer saveUser(User user) {
		String pwd = user.getPassword();
		user.setPassword(pwd_encode.encode(pwd));
		return repo.save(user).getId();
	}

	public Optional<User> findByEmail(String email) {
		return repo.findByEmail(email);
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//username (emailId)
		//1.load model class user from Data Base
		Optional<User> opt = findByEmail(username);
		//2.user not exit
		if(!opt.isPresent())
		{
			throw new UsernameNotFoundException("User Not Exits");
		}
		else
		{
			//3.Read model class user object data
			User user = opt.get();
			//4.convert set<String> to Set<GA>
			Set<String> role = user.getRole();
			
			Set<GrantedAuthority> authorities=new HashSet<GrantedAuthority>();
			for(String ro:role) {
				authorities.add(new SimpleGrantedAuthority(ro));   
			} 
			//5.return spring security user object data

			return new org.springframework.security.core.userdetails.User(
					username, 
					user.getPassword(),
					authorities);
		}

	}	

}
