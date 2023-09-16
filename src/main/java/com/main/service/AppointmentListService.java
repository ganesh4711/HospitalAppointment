package com.main.service;

import com.main.RequestDto.AppointmentListDto;
import com.main.entites.AppointmentList;
import com.main.repos.AppointmentListRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.List;
@AllArgsConstructor
public class AppointmentListService {
    private final ModelMapper modelMapper;
    private final AppointmentListRepo appointmentListRepo;
    public AppointmentListDto convertEntityToDto(AppointmentList appointmentList){
        return modelMapper.map(appointmentList,AppointmentListDto.class);
    }
    public AppointmentList convertDtoToEntity(AppointmentListDto appointmentListDto){
        return modelMapper.map(appointmentListDto,AppointmentList.class);
    }

    public List<AppointmentListDto> getAllAppointmentList() {
        return appointmentListRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .toList();
    }
    public List<AppointmentListDto> getAllByDoctor(Integer doctorId){
        List<AppointmentList> allByDoctorDoctorId = appointmentListRepo.findAllByDoctor_DoctorId(doctorId);
        return allByDoctorDoctorId.stream()
                .map(this::convertEntityToDto)
                .toList();
    }
}
