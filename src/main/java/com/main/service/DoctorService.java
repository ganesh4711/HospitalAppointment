package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entites.Doctor;
import com.main.repos.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepo;
  public List<Doctor> getAllDoctors(){
	  return doctorRepo.findAll();
  }
	
}
