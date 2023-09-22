package com.main.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.main.entites.Appointment;
import com.main.service.AppointmentsService;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentsService appointService;
	
	@GetMapping("/all") //admin or staff
	public List<Appointment> retriveAppointments(){
		return appointService.getAppointments() ;
		
	}
	@GetMapping("/user") //patient
	public List<Appointment> retriveAppointmentsOfUser(){
		return appointService.getAppointmentsOfUser() ;
		
	}
	@GetMapping("/doctor")  //DOCTOR
	public List<Appointment> retriveAppointmentsOfDoctor(){
		return appointService.getAppointmentsOfDoctor() ;
		
	}
	@PostMapping("/schedule")
	public Appointment addAppointment(@RequestBody @Valid Appointment a) {
		return appointService.fixAppointment(a);
	}
}
