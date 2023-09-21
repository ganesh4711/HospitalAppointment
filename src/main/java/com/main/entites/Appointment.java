package com.main.entites;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="appointments")
@Data
public class Appointment {

    @Id
    private Integer appointmentId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "reception_staff_id")
    private ReceptionStaff receptionStaff;
	private String type;

    private LocalDate dateOfAppointment;
    private LocalTime timeOfAppointment;
    private Boolean status;
 // Getter and setter methods

    
}
