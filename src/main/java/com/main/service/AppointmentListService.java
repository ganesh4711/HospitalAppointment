package com.main.service;

import com.main.RequestDto.AppointmentListDto;
import com.main.entites.Appointment;
import com.main.entites.AppointmentList;
import com.main.entites.Doctor;
import com.main.entites.Patient;
import com.main.globalExcp.BussinessException;
import com.main.repos.AppointmentListRepo;
import com.main.repos.AppointmentRepository;
import com.main.repos.DoctorRepository;
import com.main.repos.PatientRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AppointmentListService {
    private final ModelMapper modelMapper;
    private final AppointmentListRepo appointmentListRepo;
    private final AppointmentRepository appointmentRepo;
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

        if (appointmentListDto == null) {
            throw new IllegalArgumentException("Appointment data cannot be null");
        }

        // Validate appointment date
        LocalDate currentDate = LocalDate.now();
        if (appointmentListDto.getDateOfAppointment().isBefore(currentDate)) {
            throw new BussinessException("Invalid Date... enter correct date for appointment");
        }

        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        LocalTime appointmentTime = appointmentListDto.getTimeOfAppointment();
        if (appointmentTime.isBefore(startTime) || appointmentTime.isAfter(endTime)) {
            throw new BussinessException("Appointment timings should be between 10:00 AM and 6:00 PM");
        }

        Optional<Patient> patientOptional = patientRepository.findById(appointmentListDto.getPatientId());
        if (patientOptional.isEmpty() || !patientOptional.get().getStatus()) {
            throw new BussinessException("Invalid Patient");
        }

        Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentListDto.getDoctorId());
        if (doctorOptional.isEmpty() || !doctorOptional.get().getStatus() || !doctorOptional.get().getType().equals(appointmentListDto.getType())) {
            throw new BussinessException("Invalid Doctor");
        }
        appointmentListDto.setStatus(null);
        AppointmentList appointmentList = appointmentListRepo.save(convertDtoToEntity(appointmentListDto));
        return convertEntityToDto(appointmentList);
    }
    //partial update for date time and status
    public AppointmentListDto approveAppointment(Integer id,AppointmentListDto appointmentListDto){
        Optional<AppointmentList> optionalAppointmentList = appointmentListRepo.findById(id);
        if (optionalAppointmentList.isEmpty()){
            throw new NoSuchElementException();
        }
        AppointmentList appointmentList=optionalAppointmentList.get();

        if (!appointmentListDto.getStatus()){
            appointmentList.setStatus(false);
            return convertEntityToDto(appointmentListRepo.save(appointmentList));
        }
       else  {


            //approved appointment saving in appointments
            appointmentList.setDateOfAppointment(appointmentListDto.getDateOfAppointment());
            appointmentList.setTimeOfAppointment(appointmentListDto.getTimeOfAppointment());
            appointmentList.setStatus(true);

            Appointment appointment = modelMapper.map(appointmentList, Appointment.class);
            appointment.setAppointmentId(id);

            // Save the new Appointment entity
            appointmentRepo.save(appointment);
            //saving status true
            AppointmentList save = appointmentListRepo.save(appointmentList);
            return convertEntityToDto(save);
        }


    }
    public void deleteEmployee(int id){
        Optional<AppointmentList> optionalAppointmentList = appointmentListRepo.findById(id);
        if (optionalAppointmentList.isEmpty()){
            throw new NoSuchElementException();
        }
       AppointmentList appointmentList=optionalAppointmentList.get();
        appointmentList.setStatus(false);
        appointmentListRepo.save(appointmentList);
    }
}
