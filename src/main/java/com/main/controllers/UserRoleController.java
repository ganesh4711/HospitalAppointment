package com.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.main.entites.UserRole;
import com.main.service.UserRoleService;

@RestController
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;

	@GetMapping("/user-roles")
	public List<UserRole> retriveAll() {
		return userRoleService.getAll();
	}
	@GetMapping("/role/{role}")
	public List<UserRole> retriveByRole(@PathVariable String role){
		
		return userRoleService.getByRole(role);
	}
}
