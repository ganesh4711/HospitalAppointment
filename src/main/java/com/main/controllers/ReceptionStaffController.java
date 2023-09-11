package com.main.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.main.ApiResponse;
import com.main.RequestDto.PatientDto;
import com.main.RequestDto.ReceptionStaffDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.main.entites.ReceptionStaff;
import com.main.repos.ReceptionStaffRepository;
import com.main.service.ReceptionStaffService;

import javax.validation.Valid;

@RestController
@RequestMapping("/staff")
public class ReceptionStaffController {

	@Autowired
	private ReceptionStaffService receptionService;

	/**
	 *
	 * @return staff member info
	 */
	@GetMapping("/{id}")
	public ReceptionStaffDto getStaffInfoById(@PathVariable int id){
		return receptionService.findReceptionInfoWithId(id);
	}


	/**
	 *
	 * @return staff members info
	 */
	@GetMapping("all")
	public ApiResponse<List<ReceptionStaffDto>> getStaffInfo(){
		Map<String, Object> data = new HashMap<>();
		data.put("Staff", receptionService.findAllStaffInfo());
		return new ApiResponse<>(HttpStatus.OK,"SUCCESS",data);
	}
	/**
	 * @param offset   page start from 1
	 * @param pagesize of the page
	 * @return page of patients
	 */
	@GetMapping("/{offset}/{pagesize}")
	public ApiResponse<ReceptionStaffDto> retriveAllWithPagination(@PathVariable int offset, @PathVariable int pagesize) {
		if (offset < 1 || pagesize < 1) {
			throw new IllegalArgumentException();
		}
		Page<ReceptionStaffDto> allStaffWithPagination = receptionService.findAllStaffWithPagination((offset-1), pagesize);
		if (offset <= allStaffWithPagination.getTotalPages()) {
			Map<String, Object> data = new HashMap<>();
			data.put("Staff", allStaffWithPagination.getContent());
			Map<String, Object> meta = Map.of("pageNo", offset,
					"pageSize", allStaffWithPagination.getSize(),
					"pageCount", allStaffWithPagination.getNumberOfElements(),
					"recordCount", allStaffWithPagination.getTotalElements(),
					"noOfPages", allStaffWithPagination.getTotalPages());

			return new ApiResponse<>(HttpStatus.OK, "SUCCESS", data, meta);

		} else
			return new ApiResponse<>(HttpStatus.NO_CONTENT, "Empty page");
	}

	/**
	 * @param offset   page
	 * @param pageSize of the page
	 * @param field    to be sorted
	 * @return page of staff
	 */
	@GetMapping("/{offset}/{pageSize}/{field}")
	public ApiResponse<ReceptionStaffDto> retriveAllWithPaginationSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
		if (offset < 1 || pageSize < 1) {
			throw new IllegalArgumentException();
		}
		Page<ReceptionStaffDto> allPatientsWithPagination = receptionService.findAllStaffWithPaginationSorting((offset-1), pageSize,field);
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
	 * @param receptionStaffDto object
	 * @return staff info
	 */
	@PostMapping("/signup")
	public ReceptionStaffDto addReceptionStaff(@RequestBody @Valid ReceptionStaffDto receptionStaffDto) {
		return receptionService.addStaff(receptionStaffDto);
	}

	/**
	 *
	 * @param id of staff
	 * @return deactive status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deletePatientWithId(@PathVariable int id){
		if (receptionService.deleteStaffWithId(id)){
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT,"Patient Deleted"),HttpStatus.NO_CONTENT);
		}
		else  throw new NoSuchElementException();
	}

}
