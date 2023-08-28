package com.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.entites.ReceptionStaff;
import com.main.repos.ReceptionStaffRepository;

@Service
public class ReceptionStaffService {

	@Autowired
	private ReceptionStaffRepository receptionRepo;
	
	public List<ReceptionStaff> getStaff(){
		return receptionRepo.findAll();
	}
}
