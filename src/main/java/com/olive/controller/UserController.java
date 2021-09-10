package com.olive.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olive.model.User;
import com.olive.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService service;

	@GetMapping("/show")
	public String show() {
		return "UserRegistration";
	}

	@PostMapping("/save")
	public String show(@ModelAttribute User user, Model model) {
		Integer id = service.saveUser(user);
		model.addAttribute("message", "User " +id+ "Created !!");
		return "UserRegistration";
	}

	@GetMapping("/home")
	public String showHome() {
		return "Home";
	}	

	@GetMapping("/common")
	public String showCommon() {
		//1. read the SecurityContext object
		SecurityContext sc = SecurityContextHolder.getContext();
		//2.read the Authentication reference from sc
		 Authentication auth = sc.getAuthentication();
		 //3.read the username
		 System.out.println(auth.getName());
		 //4.read the role
		        auth.getAuthorities()
		        .stream()
		        .map(role->role.getAuthority())
		        .forEach(System.out::println);
		 //5 you can check impl class
		       System.out.println(auth.getClass().getName());
		return "Common";
	}	

	@GetMapping("/employee")
	public String showEmp() {
		return "Employee";
	}	

	@GetMapping("/admin")
	public String showAdmin() {
		return "Admin";
	}	
	@GetMapping("/denied")
	public String showDenied() {
		return "Denied";
	}

	@GetMapping("/manager")
	public String showManager() {
		return "Manager";
	}

	@GetMapping("/user")
	public String showUser() {
		return "User";
	}

	@GetMapping("/login")
	public String customLogin() {
		return "UserLoginPage";
	}

}
