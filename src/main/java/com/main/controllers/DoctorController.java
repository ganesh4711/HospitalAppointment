package com.main.controllers;

import java.util.List;

import com.main.RequestDto.DoctorDto;
import com.main.customExceptions.BussinessException;
import com.main.responseDto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entites.Doctor;
import com.main.service.DoctorService;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
@Autowired
private DoctorService doctorService;
@GetMapping("/all/doctors")
public List<DoctorDto> retriveAll(){
	return doctorService.getAllDoctors();
}
	@GetMapping("/{type}")
	public ResponseEntity<ApiResponse<List<DoctorDto>>> getDoctorsByType(@PathVariable String type){
		List<DoctorDto> doctorsByType = doctorService.getDoctorsByType(type);
		if (!doctorsByType.isEmpty()){
			return new ResponseEntity<>(new ApiResponse<>(doctorsByType), HttpStatus.OK);
		}
		else throw new BussinessException("Invalid type..! Doctors not available for "+type);
	}
}