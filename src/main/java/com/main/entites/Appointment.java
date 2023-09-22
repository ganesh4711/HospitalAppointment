package com.main.entites;


import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="appointments")
@Data
public class Appointment {

    @Id
    private Integer appointmentId;
    @Column(name="doctor_id")
    private Integer doctorId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "doctor_id" , insertable = false,updatable = false)
    private Doctor doctor;

    @Column(name="patient_id")
    private Integer patientId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_id",insertable = false,updatable = false)
    private Patient patient;

    @Column(name="reception_staff_id")
    private Integer staffId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reception_staff_id",insertable = false,updatable = false)
    private ReceptionStaff receptionStaff;
	private String type;

    private LocalDate dateOfAppointment;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeOfAppointment;
    private Boolean status;
 // Getter and setter methods

    
}
