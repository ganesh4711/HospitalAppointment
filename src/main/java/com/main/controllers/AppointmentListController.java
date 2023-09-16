package com.main.controllers;

import com.main.ApiResponse;
import com.main.RequestDto.AppointmentListDto;
import com.main.service.AppointmentListService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
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

}
