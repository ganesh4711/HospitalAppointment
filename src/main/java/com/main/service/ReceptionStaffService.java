package com.main.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.RequestDto.ReceptionStaffDto;
import com.main.entites.ReceptionStaff;
import com.main.repos.ReceptionStaffRepository;

@Service
public class ReceptionStaffService {

	@Autowired
	private ReceptionStaffRepository receptionRepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	public ReceptionStaffDto convertEntityToDto(ReceptionStaff staff ) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(staff, ReceptionStaffDto.class);
	}
	
	public List<ReceptionStaff> getStaff(){
		return receptionRepo.findAll();
	}
}
