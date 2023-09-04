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

import lombok.Data;

public class AppointmentDto {

    private Integer appointmentId;


    private DoctorDto doctor;

   
    private PatientDto patient;

    private ReceptionStaffDto receptionStaff;

    private Date dateOfAppointment;
    private Time timeOfAppointment;
    private Boolean status;

    
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public DoctorDto getDoctor() {
		return doctor;
	}
	public void setDoctor(DoctorDto doctor) {
		this.doctor = doctor;
	}
	public PatientDto getPatient() {
		return patient;
	}
	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}
	public ReceptionStaffDto getReceptionStaff() {
		return receptionStaff;
	}
	public void setReceptionStaff(ReceptionStaffDto receptionStaff) {
		this.receptionStaff = receptionStaff;
	}
	public Date getDateOfAppointment() {
		return dateOfAppointment;
	}
	public void setDateOfAppointment(Date dateOfAppointment) {
		this.dateOfAppointment = dateOfAppointment;
	}
	public Time getTimeOfAppointment() {
		return timeOfAppointment;
	}
	public void setTimeOfAppointment(Time timeOfAppointment) {
		this.timeOfAppointment = timeOfAppointment;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
