package com.main.entites;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private User user;

    private String patientName;

    private Boolean status;
    
	public Patient(Integer patientId) {
		super();
		this.patientId = patientId;
	}
	public Patient() {
		super();
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
    
    
}
