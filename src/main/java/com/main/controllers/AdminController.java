package com.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entites.Admin;
import com.main.entites.Log;
import com.main.service.AdminService;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;

	@GetMapping("/all/admins")
	public List<Admin> retriveAll() {
		return adminService.getAll();
	}
	@GetMapping("/get/logs")
	public List<Log> retriveLogs(){
		return adminService.getLogs();
	}
}
