package com.main.controllers;

import com.main.RequestDto.DoctorDto;
import com.main.RequestDto.ReceptionStaffDto;
import com.main.customExceptions.BussinessException;
import com.main.responseDto.ApiResponse;
import com.main.service.ReceptionStaffService;
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
@RequestMapping("/staff")
public class ReceptionStaffController {

	@Autowired
	private ReceptionStaffService receptionService;

	/**
	 *
	 * @return staff member info
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ReceptionStaffDto>> getStaffInfoById(@PathVariable int id){
		return new ResponseEntity<>(new ApiResponse<>(receptionService.findReceptionInfoWithId(id)),HttpStatus.OK);
	}


	/**
	 *
	 * @return staff members info
	 */
	@GetMapping("all")
	public ResponseEntity<ApiResponse<List<ReceptionStaffDto>>> getStaffInfo(){

		return new ResponseEntity<>(new ApiResponse<>(receptionService.findAllStaffInfo()),HttpStatus.OK);
	}
	/**
	 * @param offset   page start from 1
	 * @param pagesize of the page
	 * @return page of patients
	 */
	@GetMapping("/{offset}/{pagesize}")
	public ResponseEntity<ApiResponse<List<ReceptionStaffDto>>> retriveAllWithPagination(@PathVariable int offset, @PathVariable int pagesize) {
		if (offset < 1 || pagesize < 1) {
			throw new IllegalArgumentException();
		}
		Page<ReceptionStaffDto> allStaffWithPagination = receptionService.findAllStaffWithPagination((offset-1), pagesize);
		if (offset <= allStaffWithPagination.getTotalPages()) {
			Map<String, Object> meta = Map.of("pageNo", offset,
					"pageSize", allStaffWithPagination.getSize(),
					"pageCount", allStaffWithPagination.getNumberOfElements(),
					"recordCount", allStaffWithPagination.getTotalElements(),
					"noOfPages", allStaffWithPagination.getTotalPages());

			return new ResponseEntity<>(new ApiResponse<>(allStaffWithPagination.getContent()),HttpStatus.OK);

		} else
			return new ResponseEntity<>(new ApiResponse<>(null),HttpStatus.NO_CONTENT);
	}

	/**
	 * @param offset   page
	 * @param pageSize of the page
	 * @param field    to be sorted
	 * @return page of staff
	 */
	@GetMapping("/{offset}/{pageSize}/{field}")
	public ResponseEntity<ApiResponse<List<ReceptionStaffDto>>> retriveAllWithPaginationSorting(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
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

			return new ResponseEntity<>(new ApiResponse<>(allPatientsWithPagination.getContent()),HttpStatus.OK);

		} else
			return new ResponseEntity<>(new ApiResponse<>(null),HttpStatus.NO_CONTENT);
	}

	/**
	 *
	 * @param receptionStaffDto object
	 * @return staff info
	 */
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<ReceptionStaffDto>> addReceptionStaff(@RequestBody @Valid ReceptionStaffDto receptionStaffDto) {
		return new ResponseEntity<>(new ApiResponse<>(receptionService.addStaff(receptionStaffDto)),HttpStatus.NO_CONTENT);
	}

	/**
	 *
	 * @param id of staff
	 * @return deactive status
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deletePatientWithId(@PathVariable int id){
		if (receptionService.deleteStaffWithId(id)){
			return new ResponseEntity<>(new ApiResponse<Void>(),HttpStatus.NO_CONTENT);
		}
		else  throw new NoSuchElementException();
	}
	@GetMapping("doctors/{type}")
	public ResponseEntity<ApiResponse<List<DoctorDto>>> getDoctorsByType(@PathVariable String type){
		List<DoctorDto> doctorsByType = receptionService.getDoctorsByType(type);
		if (!doctorsByType.isEmpty()){
			return new ResponseEntity<>(new ApiResponse<>(doctorsByType),HttpStatus.OK);
		}
		else throw new BussinessException("Invalid type");
	}

}
