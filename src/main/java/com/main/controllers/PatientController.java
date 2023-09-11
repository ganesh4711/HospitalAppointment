package com.main.controllers;

import com.main.ApiResponse;
import com.main.RequestDto.PatientDto;
import com.main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    /**
     *
     * @return patient info
     */
    @GetMapping
    public ApiResponse<PatientDto> retrivePatientDetails() {
        Map<String, Object> data = new HashMap<>();
        data.put("Details", patientService.getPatientDetails());
        return new ApiResponse<>(HttpStatus.OK, "success", data);
    }

    /**
     *
     * @return group of patients
     */
    @GetMapping("/all")
    public ApiResponse<List<PatientDto>> getAllPatients() {
        Object patientList = patientService.getAllPatient();
        Map<String, Object> data = new HashMap<>();
        data.put("patients", patientList);
        return new ApiResponse<>(HttpStatus.OK, "sucess", data);
    }

    /**
     * @param offset   page start from 1
     * @param pagesize of the page
     * @return page of patients
     */
    @GetMapping("/{offset}/{pagesize}")
    public ApiResponse<PatientDto> retriveAllWithPagination(@PathVariable int offset, @PathVariable int pagesize) {
        if (offset < 1 || pagesize < 1) {
            throw new IllegalArgumentException();
        }
        Page<PatientDto> allPatientsWithPagination = patientService.findAllUserWithPagination((offset-1), pagesize);
        if (offset <= allPatientsWithPagination.getTotalPages()) {
            Map<String, Object> data = new HashMap<>();
            data.put("user", allPatientsWithPagination.getContent());
            Map<String, Object> meta = Map.of("pageNo", offset,
                    "pageSize", allPatientsWithPagination.getSize(),
                    "pageCount", allPatientsWithPagination.getNumberOfElements(),
                    "recordCount", allPatientsWithPagination.getTotalElements(),
                    "noOfPages", allPatientsWithPagination.getTotalPages());

            return new ApiResponse<>(HttpStatus.OK, "SUCCESS", data, meta);

        } else
            return new ApiResponse<>(HttpStatus.NO_CONTENT, "Empty page");
    }

    /**
     * @param offset   page
     * @param pageSize of the page
     * @param field    to be sorted
     * @return page of patients
     */
    @GetMapping("/{offset}/{pageSize}/{field}")
    public ApiResponse<PatientDto> retriveAllWithPaginationSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        if (offset < 1 || pageSize < 1) {
            throw new IllegalArgumentException();
        }
        Page<PatientDto> allPatientsWithPagination = patientService.findAllPatientsWithPaginationSorting((offset-1), pageSize,field);
        if (offset <= allPatientsWithPagination.getTotalPages()) {
            Map<String, Object> data = new HashMap<>();
            data.put("user", allPatientsWithPagination.getContent());
            Map<String, Object> meta = Map.of("pageNo", offset,
                    "pageSize", allPatientsWithPagination.getSize(),
                    "pageCount", allPatientsWithPagination.getNumberOfElements(),
                    "recordCount", allPatientsWithPagination.getTotalElements(),
                    "noOfPages", allPatientsWithPagination.getTotalPages());

            return new ApiResponse<>(HttpStatus.OK, "SUCCESS", data, meta);

        } else
            return new ApiResponse<>(HttpStatus.NO_CONTENT, "Empty page");
    }



    /**
     *
     * @param patientDto object
     * @return patient info
     */
    @PostMapping("/signup")
    public PatientDto addPatient(@RequestBody @Valid PatientDto patientDto) {
        return patientService.addPatient(patientDto);
    }

    /**
     *
     * @return patient status
     */
    @DeleteMapping  //user
    public ApiResponse<Void> patientDeleted() {
        if (patientService.deletePatient()) {
            return new ApiResponse<>(HttpStatus.NO_CONTENT, "Deleted");
        } else return new ApiResponse<>(HttpStatus.NOT_FOUND, "Patient is not found");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatientWithId(@PathVariable int id){
        if (patientService.deletePatientWithId(id)){
            return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT,"Patient Deleted"),HttpStatus.NO_CONTENT);
        }
        else  throw new NoSuchElementException();
    }
}
