package com.main.controllers;

import com.main.RequestDto.AppointmentListDto;
import com.main.responseDto.ApiResponse;
import com.main.service.AppointmentListService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/appointmentList")
public class AppointmentListController {
    private final AppointmentListService appointmentListService;
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<AppointmentListDto>>> retriveAll(){
        return new ResponseEntity<>(new ApiResponse<>(appointmentListService.getAllAppointmentList()), HttpStatus.OK);
    }

    @GetMapping("/all/{doctorId}")
    public ResponseEntity<ApiResponse<List<AppointmentListDto>>> retriveAll(@PathVariable Integer doctorId){
        return new ResponseEntity<>(new ApiResponse<>(appointmentListService.getAllByDoctor(doctorId)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AppointmentListDto>> addAppointmentList(@Valid @RequestBody AppointmentListDto appointmentListDto){
        return new ResponseEntity<>(new ApiResponse<>(appointmentListService.appointmentRequest(appointmentListDto)),HttpStatus.CREATED);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AppointmentListDto>> updatedAppointmentList(@RequestBody AppointmentListDto appointmentListDto,@PathVariable Integer id){
        return new ResponseEntity<>(new ApiResponse<>(appointmentListService.approveAppointment(id,appointmentListDto)),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateAppointmentList(@PathVariable int id){
        return  new ResponseEntity<>(new ApiResponse<>(),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/today")
    public ResponseEntity<ApiResponse<List<AppointmentListDto>>> appointmentsToday(){
        return new ResponseEntity<>(new ApiResponse<>(appointmentListService.getAllAppointmentListToday()),HttpStatus.OK);
    }

}
