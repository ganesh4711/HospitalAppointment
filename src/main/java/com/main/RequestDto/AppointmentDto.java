package com.main.RequestDto;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Integer appointmentId;

	@NotNull
    private DoctorDto doctor;

   @NotNull
    private PatientDto patient;
	@NotNull
    private ReceptionStaffDto receptionStaff;
	@NotNull
    private Date dateOfAppointment;
	@NotNull
    private Time timeOfAppointment;
    private Boolean status;
	
}
