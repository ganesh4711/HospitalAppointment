package com.main.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.main.entites.Patient;
import com.main.entites.User;
import com.main.globalExcp.BussinessException;
import com.main.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.main.RequestDto.DoctorDto;
import com.main.entites.Doctor;
import com.main.repos.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public DoctorDto convertEntityToDto(Doctor doctor ) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper.map(doctor, DoctorDto.class);
	}
//	------------------

	public Doctor convertDtoToEntity(DoctorDto doctorDto) {

		return modelMapper.map(doctorDto, Doctor.class);
	}

	/**
	 *
	 * @return Doctor info
	 */
	public DoctorDto getDoctorDetails() {
		int doctorId = 409;         //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
		return convertEntityToDto(doctorRepo.findById(doctorId).get());
	}

	/**
	 *
	 * @return group of doctors info
	 */
	public List<DoctorDto> getAllDoctors() {          //admin
		List<DoctorDto> doctorDtoList = doctorRepo.findAll()
				.stream()
				.map(this::convertEntityToDto)
				.toList();
		return doctorDtoList;
	}
	/**
	 * @param offSet   page
	 * @param pagesize of the page
	 * @return page
	 */
	public Page<DoctorDto> findAllDoctorWithPagination(int offSet, int pagesize) {
		Page<Doctor> doctorPage = doctorRepo.findAll(PageRequest.of(offSet, pagesize));
		List<DoctorDto> doctorDtoList = doctorPage.getContent().stream()
				.map(this::convertEntityToDto)
				.toList();

		return new PageImpl<>(doctorDtoList, doctorPage.getPageable(), doctorPage.getTotalElements());
	}

	/**
	 * @param offSet   page
	 * @param pagesize of the page
	 * @param field    to be sorted
	 * @return page
	 */
	public Page<DoctorDto> findAllDoctorsWithPaginationSorting(int offSet, int pagesize, String field) {

		Page<Doctor> doctorPage = doctorRepo.findAll(PageRequest.of(offSet, pagesize, Sort.by(field)));
		List<DoctorDto> doctorDtoList = doctorPage.getContent().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());

		return new PageImpl<>(doctorDtoList, doctorPage.getPageable(), doctorPage.getTotalElements());
	}


	/**
	 *
	 * @param  doctorDto  to add
	 * @return patient info
	 */
	public DoctorDto addDoctor(DoctorDto doctorDto) {
		User user = userRepo.findById(25).get();
		if (user.getStatus()) {
			if (doctorDto != null) {
				doctorDto.setDoctorName(user.getName());
				doctorDto.setStatus(true);
				Doctor doctor = convertDtoToEntity(doctorDto);
				return convertEntityToDto(doctorRepo.save(doctor));

			} else throw new BussinessException("doctor Must not Be Null");
		} else throw new BussinessException("Deactivated User Unable to Process...");
	}

	/**
	 *
	 * @return STATUS
	 */
	public Boolean deletePatient() {
		int uid = 25;         //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
		Doctor doctor = doctorRepo.findByUser_Id(uid);
		if (doctor != null) {
			doctor.setStatus(false);
			doctorRepo.save(doctor);
			return true;
		} else
			return false;
	}

	/**
	 *
	 * @param id patient to be deactivated
	 * @return execution status
	 */
	public boolean deletePatientWithId(int id) {
		Optional<Doctor> optional = doctorRepo.findById(id);
		if (optional.isPresent()){
			Doctor doctor = optional.get();
			doctor.setStatus(false);
			doctorRepo.save(doctor);
			return true;
		}
		else
			return false;
	}


}
