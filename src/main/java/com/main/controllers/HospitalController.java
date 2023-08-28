package com.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entites.Hospital;
import com.main.repos.HospitalRepository;

@RestController
public class HospitalController {
	@Autowired
	private HospitalRepository hospitalRepo;
	
	@GetMapping("/all/hospitals")
	public List<Hospital> retriveHospitals(){
		return hospitalRepo.findAll();
	}
}
