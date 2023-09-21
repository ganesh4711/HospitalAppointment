package com.main.controllers;

import com.main.RequestDto.PatientDto;
import com.main.responseDto.ApiResponse;
import com.main.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<PatientDto>> retrivePatientDetails() {
        return new ResponseEntity<>(new ApiResponse<>(patientService.getPatientDetails()),HttpStatus.OK);
    }

    /**
     *
     * @return group of patients
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PatientDto>>> getAllPatients() {

        return new ResponseEntity<>(new ApiResponse<>(patientService.getAllPatient()),HttpStatus.OK);
    }

    /**
     * @param offset   page start from 1
     * @param pagesize of the page
     * @return page of patients
     */
    @GetMapping("/{offset}/{pagesize}")
    public ResponseEntity<ApiResponse<List<PatientDto>>> retriveAllWithPagination(@PathVariable int offset, @PathVariable int pagesize) {
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


            return new ResponseEntity<>(new ApiResponse<>(allPatientsWithPagination.getContent(), meta),HttpStatus.OK);

        } else
            return new ResponseEntity<>(new ApiResponse<>(null),HttpStatus.NO_CONTENT);
    }

    /**
     * @param offset   page
     * @param pageSize of the page
     * @param field    to be sorted
     * @return page of patients
     */
    @GetMapping("/{offset}/{pageSize}/{field}")
    public ResponseEntity<ApiResponse<List<PatientDto>>> retriveAllWithPaginationSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
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

            return new ResponseEntity<>(new ApiResponse<>(allPatientsWithPagination.getContent(), meta),HttpStatus.OK);

        } else
            return new ResponseEntity<>(new ApiResponse<>(null),HttpStatus.NO_CONTENT);
    }



    /**
     *
     * @param userId object
     * @return patient info
     */
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<PatientDto>> addPatient(@PathVariable Integer userId) {
        return new ResponseEntity<>(new ApiResponse<>(patientService.addPatient(userId)),HttpStatus.CREATED);
    }

    /**
     *
     * @return patient status
     */
    @DeleteMapping  //user
    public ResponseEntity<ApiResponse<Void>> patientDeleted() {
        if (patientService.deletePatient()) {
            return new ResponseEntity<>(new ApiResponse<Void>(),HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(new ApiResponse<>(),HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatientWithId(@PathVariable int id){
        if (patientService.deletePatientWithId(id)) {
            return new ResponseEntity<>(new ApiResponse<Void>(),HttpStatus.NO_CONTENT);
        }
        else  throw new NoSuchElementException();
    }
}
