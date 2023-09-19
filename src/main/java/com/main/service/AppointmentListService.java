package com.main.service;

import com.main.RequestDto.AppointmentListDto;
import com.main.entites.AppointmentList;
import com.main.entites.Doctor;
import com.main.entites.Patient;
import com.main.globalExcp.BussinessException;
import com.main.repos.AppointmentListRepo;
import com.main.repos.DoctorRepository;
import com.main.repos.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
public class AppointmentListService {
    private final ModelMapper modelMapper;
    private final AppointmentListRepo appointmentListRepo;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
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
    public AppointmentListDto appointmentRequest(AppointmentListDto appointmentListDto) {
        AppointmentList appointmentList=null;
        Optional<Patient> byId = patientRepository.findById(appointmentListDto.getPatientId());
        if (byId.isPresent()) {
            Patient patient = byId.get();
            if (patient.getStatus().equals(true)) {
                Optional<Doctor> optionalDoctor = doctorRepository.findById(appointmentListDto.getDoctorId());
                if (optionalDoctor.isPresent()) {
                    Doctor doctor = optionalDoctor.get();
                    if (doctor.getStatus().equals(true) && doctor.getType().equals(appointmentListDto.getType())) {
                        if (!appointmentListDto.getDateOfAppointment().isBefore(LocalDate.now())){
                            LocalTime startTime = LocalTime.of(10, 0);
                            LocalTime endTime = LocalTime.of(18, 0);
                            if (appointmentListDto.getTimeOfAppointment().isAfter(startTime) && appointmentListDto.getTimeOfAppointment().isBefore(endTime))
                            { appointmentListDto.setStatus(null);
                                appointmentList = appointmentListRepo.save(convertDtoToEntity(appointmentListDto));
                            }
                            else throw new BussinessException("Appointment timings with in 10:00 - 6:00 PM")
                        }
                        else throw new BussinessException("Invalid Date ");
                    }
                    else throw new BussinessException("Invalid Doctor ");
                }else throw new NoSuchElementException();
            }
            else throw new BussinessException("Invalid Patient");
        }
        return convertEntityToDto(appointmentList);
    }
}
