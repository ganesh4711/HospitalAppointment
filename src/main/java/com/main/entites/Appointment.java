package com.main.entites;

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

@Entity
@Table(name="appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private Date dateOfAppointment;
    private Time timeOfAppointment;
    private Boolean status;
 // Getter and setter methods
    
	public Integer getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public ReceptionStaff getReceptionStaff() {
		return receptionStaff;
	}
	public void setReceptionStaff(ReceptionStaff receptionStaff) {
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
