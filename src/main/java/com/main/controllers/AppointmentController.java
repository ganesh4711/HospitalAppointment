package com.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.entites.Appointment;
import com.main.service.AppointmentsService;

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentsService appointService;
	
	@GetMapping("/getAppointments") //admin or staff
	public List<Appointment> retriveAppointments(){
		return appointService.getAppointments() ;
		
	}
	@GetMapping("/user/getAppointments") //patient
	public List<Appointment> retriveAppointmentsOfUser(){
		return appointService.getAppointmentsOfUser() ;
		
	}
	@GetMapping("/doctor/getAppointments") //patient
	public List<Appointment> retriveAppointmentsOfDoctor(){
		return appointService.getAppointmentsOfDoctor() ;
		
	}
	@PostMapping("/staff/fix/appointment")
	public Appointment addAppointment(@RequestBody Appointment a) {
		return appointService.fixAppointment(a);
	}
}
