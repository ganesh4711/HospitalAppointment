package com.main.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.main.RequestDto.AdminDto;
import com.main.RequestDto.AppointmentDto;
import com.main.entites.Admin;
import com.main.entites.Appointment;
import com.main.entites.ReceptionStaff;
import com.main.repos.AppointmentRepository;
import com.main.repos.DoctorRepository;
import com.main.repos.PatientRepository;

@Service
public class AppointmentsService {

	@Autowired
	private AppointmentRepository appointRepo;
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	private PatientRepository patientRepo;
	@Autowired
	private ModelMapper modelmapper;
	
	public AppointmentDto convertEntityToDto(Appointment appointment ) {
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelmapper.map(appointment, AppointmentDto.class);
	}
	
	public List<Appointment> getAppointments(){
		return appointRepo.findAll();
	}
	
	public List<Appointment> getAppointmentsOfUser(){
		int uid = 26;       //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
        int patientId = patientRepo.findByUser_Id(uid).getPatientId();
        return appointRepo.findByPatient_PatientId(patientId);
	}
	public List<Appointment> getAppointmentsOfDoctor(){
		  int uid = 6;       //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
      int doctorId = doctorRepo.findByUser_Id(uid).getDoctorId();
      return appointRepo.findByDoctor_DoctorId(doctorId);
	}
     
	public Appointment fixAppointment(Appointment appointment) {
		if(patientRepo.findById(appointment.getPatient().getPatientId()).isPresent()) {
			if (doctorRepo.findById(appointment.getDoctor().getDoctorId()).isPresent()) {
				appointment.setReceptionStaff(new ReceptionStaff(301));    //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
				return appointRepo.save(appointment);
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid doctor id");
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid patient id");
	}
}
