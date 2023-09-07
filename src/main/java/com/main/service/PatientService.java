package com.main.service;

import com.main.RequestDto.PatientDto;

import com.main.RequestDto.UserDto;
import com.main.entites.Patient;

import com.main.entites.User;
import com.main.globalExcp.BussinessException;
import com.main.repos.PatientRepository;
import com.main.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public PatientDto convertEntityToDto(Patient patient) {

        return modelMapper.map(patient, PatientDto.class);
    }

    public Patient convertDtoToEntity(PatientDto patientDto) {

        return modelMapper.map(patientDto, Patient.class);
    }

    public List<PatientDto> getAllPatient() {          //admin
        List<PatientDto> patientDtoList = patientRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .toList();
        return patientDtoList;
    }
    /**
     * @param offSet   page
     * @param pagesize of the page
     * @return page
     */
    public Page<PatientDto> findAllUserWithPagination(int offSet, int pagesize) {
        Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(offSet, pagesize));
        List<PatientDto> userDtoList = patientPage.getContent().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, patientPage.getPageable(), patientPage.getTotalElements());
    }

    /**
     * @param offSet   page
     * @param pagesize of the page
     * @param field    to be sorted
     * @return page
     */
    public Page<PatientDto> findAllUserWithPaginationSorting(int offSet, int pagesize, String field) {

        Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(offSet, pagesize, Sort.by(field)));
        List<PatientDto> userDtoList = patientPage.getContent().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, patientPage.getPageable(), patientPage.getTotalElements());
    }


    public PatientDto getPatientDetails() {
        int patientId = 409;         //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
        return convertEntityToDto(patientRepository.findById(patientId).get());
    }

    public PatientDto addPatient(PatientDto patientDto) {
        User user = userRepository.findById(25).get();
        if (user.getStatus()) {
            if (patientDto != null) {
                patientDto.setPatientName(user.getName());
                patientDto.setStatus(true);
                Patient patient = convertDtoToEntity(patientDto);
                return convertEntityToDto(patientRepository.save(patient));

            } else throw new BussinessException("Patient Must not Be Null");
        } else throw new BussinessException("Deactivated User Unable to Process...");
    }

    public Boolean delete() {
        int uid = 25;         //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
        Patient patient = patientRepository.findByUser_Id(uid);
        if (patient != null) {
            patient.setStatus(false);
            patientRepository.save(patient);
            return true;
        } else
            return false;
    }

}
