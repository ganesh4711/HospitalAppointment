package com.main.service;

import com.main.RequestDto.AppointmentDto;
import com.main.entites.Appointment;
import com.main.entites.Doctor;
import com.main.entites.ReceptionStaff;
import com.main.repos.AppointmentRepository;
import com.main.repos.DoctorRepository;
import com.main.repos.PatientRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentsService {

	@Autowired
	private AppointmentRepository appointRepo;
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	private PatientRepository patientRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public AppointmentDto convertEntityToDto(Appointment appointment ) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(appointment, AppointmentDto.class);
	}
	public Appointment convertDtoToEntity(AppointmentDto appointmentDto) {

		return modelMapper.map(appointmentDto,Appointment.class);
	}
	
	public List<Appointment> getAppointments(){
		return appointRepo.findAll();
	}

//	-------------------------------------





	/**
	 *
	 * @return group of patients info
	 */
	public List<AppointmentDto> getAllPatient() {          //admin
		List<AppointmentDto> appointmentList = appointRepo.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.toList();
		return appointmentList;
	}
	/**
	 * @param offSet   page
	 * @param pagesize of the page
	 * @return page
	 */
	public Page<AppointmentDto> findAllAppointmentsWithPagination(int offSet, int pagesize) {
		Page<Appointment> appointmentPage = appointRepo.findAll(PageRequest.of(offSet, pagesize));
		List<AppointmentDto> appointmentDtoList = appointmentPage.getContent().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());

		return new PageImpl<>(appointmentDtoList, appointmentPage.getPageable(), appointmentPage.getTotalElements());
	}
	/**
	 * @param offSet   page
	 * @param pagesize of the page
	 * @param field    to be sorted
	 * @return page
	 */
	public Page<AppointmentDto> findAllAppointmentsWithPaginationSorting(int offSet, int pagesize, String field) {

		Page<Appointment> appointmentPage = appointRepo.findAll(PageRequest.of(offSet, pagesize, Sort.by(field)));
		List<AppointmentDto> userDtoList = appointmentPage.getContent().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());

		return new PageImpl<>(userDtoList, appointmentPage.getPageable(), appointmentPage.getTotalElements());
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
			Optional<Doctor> byId = doctorRepo.findById(appointment.getDoctor().getDoctorId());
			if (byId.isPresent()) {

					appointment.setReceptionStaff(new ReceptionStaff(301));    //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
					return appointRepo.save(appointment);
			}
			throw new NoSuchElementException("invalid doctor id ");
		}
		throw new NoSuchElementException("invalid patient id");
	}
}
