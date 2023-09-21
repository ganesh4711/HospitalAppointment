package com.main.service;

import com.main.RequestDto.PatientDto;

import com.main.entites.Patient;

import com.main.entites.User;
import com.main.customExceptions.BussinessException;
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
import java.util.NoSuchElementException;
import java.util.Optional;
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

    /**
     *
     * @return patient info
     */
    public PatientDto getPatientDetails() {
        int patientId = 409;         //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
        return convertEntityToDto(patientRepository.findById(patientId).get());
    }

    /**
     *
     * @return group of patients info
     */
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
    public Page<PatientDto> findAllPatientsWithPaginationSorting(int offSet, int pagesize, String field) {

        Page<Patient> patientPage = patientRepository.findAll(PageRequest.of(offSet, pagesize, Sort.by(field)));
        List<PatientDto> userDtoList = patientPage.getContent().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(userDtoList, patientPage.getPageable(), patientPage.getTotalElements());
    }


    /**
     *
     * @param  userId  to add
     * @return patient info
     */
    public PatientDto addPatient(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getStatus()) {
                Patient patient = new Patient();
                patient.setPatientName(user.getName());
                patient.setUser(new User(userId));
                patient.setStatus(true);
                return convertEntityToDto(patientRepository.save(patient));
            }else throw new BussinessException("Deactivated User Unable to Process...");
        } else throw new NoSuchElementException("user not found with id "+userId);
    }

    /**
     *
     * @return STATUS
     */
    public Boolean deletePatient() {
        int uid = 25;         //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
        Patient patient = patientRepository.findByUser_Id(uid);
        if (patient != null) {
            patient.setStatus(false);
            patientRepository.save(patient);
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
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isPresent()){
            optional.get().setStatus(false);
            return true;
        }
        else
            return false;
    }
}
