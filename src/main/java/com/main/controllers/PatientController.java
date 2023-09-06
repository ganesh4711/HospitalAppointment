package com.main.controllers;

import com.main.ApiResponse;
import com.main.RequestDto.PatientDto;
import com.main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {
@Autowired
    private PatientService patientService;

@GetMapping("/all")
    public ApiResponse<List<PatientDto>> getAllPatients(){
    Object patientList=patientService.getAllPatient();
    Map<String,Object> data=new HashMap<>();
    data.put("patients",patientList);
    return new ApiResponse<>(HttpStatus.OK,"sucess", data);
}
@GetMapping
    public  ApiResponse<PatientDto> retrivePatientDetails(){
    Map<String,Object> data=new HashMap<>();
    data.put("Details",patientService.getPatientDetails());
    return new ApiResponse<>(HttpStatus.OK,"sucess",data);
}
@PostMapping("/")
    public  PatientDto addPatient(@RequestBody PatientDto patientDto){
    return patientService.addPatient(patientDto);
}
@DeleteMapping("/")  //user
    public ApiResponse<Void> patientDeleted(){
    if(patientService.delete()){
        return new ApiResponse<>(HttpStatus.NO_CONTENT,"Deleted");
    }
    else return new ApiResponse<>(HttpStatus.NOT_FOUND,"Patient is not found");
}
}
