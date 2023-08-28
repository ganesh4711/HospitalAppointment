package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entites.Admin;
import com.main.entites.Log;
import com.main.repos.AdminRepository;
import com.main.repos.LogRepository;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private LogRepository logRepo;
  public List<Admin> getAll(){
	  return adminRepo.findAll();
  }
public Admin createAdmin(Admin admin) {
	
	return null;
}
public List<Log> getLogs() {
	
	return logRepo.findAll();
}
	
}
