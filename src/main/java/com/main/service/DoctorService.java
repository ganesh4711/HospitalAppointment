package com.main.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.RequestDto.AdminDto;
import com.main.RequestDto.DoctorDto;
import com.main.entites.Admin;
import com.main.entites.Doctor;
import com.main.repos.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	private ModelMapper modelmapper;
	
	public DoctorDto convertEntityToDto(Doctor doctor ) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(doctor, DoctorDto.class);
	}
	
  public List<Doctor> getAllDoctors(){
	  return doctorRepo.findAll();
  }
	
}
