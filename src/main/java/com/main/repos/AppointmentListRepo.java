package com.main.repos;

import com.main.entites.AppointmentList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentListRepo extends JpaRepository<AppointmentList,Integer> {
    List<AppointmentList> findAllByDoctor_DoctorId(Integer doctorId);
}
