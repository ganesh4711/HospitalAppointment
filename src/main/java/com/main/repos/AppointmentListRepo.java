package com.main.repos;

import com.main.entites.AppointmentList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentListRepo extends JpaRepository<AppointmentList,Integer> {
    List<AppointmentList> findAllByDoctor_DoctorId(Integer doctorId);

    @Query(value = "select * from appointmentslist where date_of_appointment=:now",nativeQuery = true)
    List<AppointmentList> getTodayAppointmentRequest(@PathParam("now") LocalDate now);
}
