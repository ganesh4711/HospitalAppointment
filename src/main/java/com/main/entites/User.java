package com.main.entites;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;
    private String address;
    private String phNo;
    
    @Column(unique = true)
   
    private String email;
    private String password;
    @Column(columnDefinition = "default 'true'")
    private Boolean status;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserRole> userRoles;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private Admin admin;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private ReceptionStaff receptionStaff;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private Doctor doctor;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JsonIgnore
    private Patient patient;
    
    
    
	public User() {
		super();
	}

	public User(Integer userId2) {
		this.userId=userId2;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public ReceptionStaff getReceptionStaff() {
		return receptionStaff;
	}

	public void setReceptionStaff(ReceptionStaff receptionStaff) {
		this.receptionStaff = receptionStaff;
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

    // Getter and setter methods
    
}
