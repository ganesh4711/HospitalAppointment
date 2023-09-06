package com.main.service;

import com.main.RequestDto.PatientDto;

import com.main.entites.Patient;

import com.main.entites.User;
import com.main.globalExcp.BussinessException;
import com.main.repos.PatientRepository;
import com.main.repos.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    public PatientDto convertEntityToDto(Patient patient ) {

        return modelMapper.map(patient, PatientDto.class);
    }
    public Patient convertDtoToEntity(PatientDto patientDto ) {

        return modelMapper.map(patientDto, Patient.class);
    }

    public List<PatientDto> getAllPatient(){          //admin
  List<PatientDto> patientDtoList= patientRepository.findAll()
                                                    .stream()
                                                      .map(this::convertEntityToDto)
                                                      .toList();
        return patientDtoList;
    }
    public Page<PatientDto> getAllPatients(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Patient> patientsPage = patientRepository.findAll(pageRequest);

        return patientsPage.map(this::convertEntityToDto);
    }
    public PatientDto getPatientDetails(){
        int patientId=409;         //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
        var patientDto=convertEntityToDto(patientRepository.findById(patientId).get());
        return  patientDto;
    }
    public PatientDto addPatient(PatientDto patientDto){
        User user=userRepository.findById(25).get();
       if (user.getStatus()) {
           if (patientDto != null) {
               patientDto.setPatientName(user.getName());
               patientDto.setStatus(true);
               Patient patient = convertDtoToEntity(patientDto);
               return convertEntityToDto(patientRepository.save(patient));

           } else throw new BussinessException("Patient Must not Be Null");
       }
       else throw new BussinessException("Deactivated User Unable to Process...");
    }
    public Boolean delete(){
        int uid=25;         //SecurityContextHolder.getContext().getAuthentication().authentication.getName();
        Patient patient=patientRepository.findByUser_UserId(uid);
        if (patient != null){
            patient.setStatus(false);
            patientRepository.save(patient);
            return true;
        }
        else
            return false;
    }

}
