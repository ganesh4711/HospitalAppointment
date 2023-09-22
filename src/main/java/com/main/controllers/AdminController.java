package com.main.controllers;

import java.util.List;

import com.main.RequestDto.AdminDto;
import com.main.responseDto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/add")
	public ResponseEntity<ApiResponse<AdminDto>> createAdmin(@RequestBody AdminDto adminDto){
		if(adminDto!=null) {
			return new ResponseEntity<>(new ApiResponse<>(adminService.addAdmin(adminDto)), HttpStatus.CREATED);
		} else
			throw new NullPointerException("invalid request admin not be null..");

	}

}
