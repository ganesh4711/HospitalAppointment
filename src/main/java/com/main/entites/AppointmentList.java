package com.main.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "appointmentslist")
public class AppointmentList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @ManyToOne
    @JoinColumn(name = "reception_staff_id", nullable = false)
    private ReceptionStaff receptionStaff;

    @Column(name = "date_of_appointment", nullable = false)
    private LocalDate dateOfAppointment;

    @Column(name = "time_of_appointment", nullable = false)
    private LocalTime timeOfAppointment;

    @Column(name = "status")
    private Boolean status;

}

