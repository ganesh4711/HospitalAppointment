package com.main.entites;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="hospitals")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hospitalId;

    private String hospitalName;
    private Date since;

    
    private String address;


	public Integer getHospitalId() {
		return hospitalId;
	}


	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}


	public String getHospitalName() {
		return hospitalName;
	}


	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}


	public Date getSince() {
		return since;
	}


	public void setSince(Date since) {
		this.since = since;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

    
}
