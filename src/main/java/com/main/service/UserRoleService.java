package com.main.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.main.RequestDto.ReceptionStaffDto;
import com.main.RequestDto.UserRoleDto;
import com.main.entites.ReceptionStaff;
import com.main.entites.Role;
import com.main.entites.User;
import com.main.entites.UserRole;
import com.main.repos.UserRoleRepository;

@Service
public class UserRoleService {
	@Autowired
	private UserRoleRepository userRoleRepo;

	@Autowired
	private ModelMapper modelmapper;

	public UserRoleDto convertEntityToDto(UserRole userroledto) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(userroledto, UserRoleDto.class);
	}

	public List<UserRole> getAll() {
		return userRoleRepo.findAll();
	}

	public List<UserRole> getByRole(@PathVariable String role) {
		if (userRoleRepo.existsByRole_RoleName(role)) {
			return userRoleRepo.findByRole_RoleName(role);
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid Role");
	}

	public ResponseEntity<UserRole> assignRole(Integer userId, Integer roleId) {
		UserRole userRole = new UserRole();
		userRole.setRole(new Role(roleId));
		userRole.setUser(new User(userId));
		userRoleRepo.save(userRole);
		return null;
	}
}
