package com.main.controllers;

import com.main.RequestDto.AppointmentListDto;
import com.main.repos.AppointmentListRepository;
import com.main.service.AppointmentListService;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/appointmentList")
public class AppointmentListController {
    private final AppointmentListService appointmentListService;
    @GetMapping("/all")
    public List<AppointmentListDto> retriveAll(){
        return appointmentListService.getAllAppointmentList();
    }

}
