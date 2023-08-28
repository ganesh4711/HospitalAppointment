package com.main.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entites.ReceptionStaff;
import com.main.repos.ReceptionStaffRepository;
import com.main.service.ReceptionStaffService;

@RestController
public class ReceptionStaffController {

	@Autowired
	private ReceptionStaffService receptionService;
	@GetMapping("/get/receptionstaff")
	public List<ReceptionStaff> getStaff(){
		return receptionService.getStaff();
	}
}
