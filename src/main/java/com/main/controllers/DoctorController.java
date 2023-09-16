package com.main.controllers;

import java.util.List;

import com.main.RequestDto.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entites.Doctor;
import com.main.service.DoctorService;

@RestController
public class DoctorController {
@Autowired
private DoctorService doctorService;
@GetMapping("/all/doctors")
public List<DoctorDto> retriveAll(){
	return doctorService.getAllDoctors();
}
}