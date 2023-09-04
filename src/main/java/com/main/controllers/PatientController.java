package com.main.controllers;

import com.main.RequestDto.PatientDto;
import com.main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
@Autowired
    private PatientService patientService;

@GetMapping("/all/patients")
    public ResponseEntity<List<PatientDto>> getAllPatients(){
    return ResponseEntity.ok(patientService.getAllPatient());
}
@GetMapping("/get/patient/details")
    public  PatientDto retrivePatientDetails(){
    return patientService.getPatientDetails();
}
@PostMapping("/create/patient")
    public  PatientDto addPatient(@RequestBody PatientDto patientDto){
    return patientService.addPatient(patientDto);
}
@DeleteMapping("/delete/patient")  //user
    public ResponseEntity<Void> patientDeleted(){
    if(patientService.delete()){
        return ResponseEntity.noContent().build();
    }
    else return ResponseEntity.notFound().build();
}
}
