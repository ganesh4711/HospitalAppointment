package com.main.service;

import java.util.List;
import java.util.Optional;

import com.main.customExceptions.BussinessException;
import com.main.customExceptions.ResourceNotFound;
import com.main.entites.User;
import com.main.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.RequestDto.AdminDto;
import com.main.entites.Admin;
import com.main.entites.Log;
import com.main.repos.AdminRepository;
import com.main.repos.LogRepository;

@Service
@AllArgsConstructor
public class AdminService {

	private final AdminRepository adminRepo;

	private final LogRepository logRepo;
	private final UserRepository userRepository;
	private final  ModelMapper modelmapper;
	
	public AdminDto convertEntityToDto(Admin admin ) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(admin, AdminDto.class);
	}
	public Admin convertDtoToEntity(AdminDto admindto ) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(admindto, Admin.class);
	}

	public List<Admin> getAll() {
		return adminRepo.findAll();
	}

	public Admin createAdmin(Admin admin) {

		return null;
	}

	public List<Log> getLogs() {

		return logRepo.findAll();
	}
	public AdminDto addAdmin(AdminDto adminDto){
		Optional<User> optionalUser = userRepository.findById(adminDto.getUserId());
		if(optionalUser.isEmpty())
			throw new ResourceNotFound("No User found with id "+adminDto.getUserId());
		User user = optionalUser.get();
		if(!user.getStatus())
		{
			throw new BussinessException("Deactivated User.. unable to process request");
		}
		adminDto.setAdminName(user.getName());
		adminDto.setStatus(true);
		Admin saved = adminRepo.save(convertDtoToEntity(adminDto));
		return convertEntityToDto(saved);
	}

}
