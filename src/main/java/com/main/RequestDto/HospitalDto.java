package com.main.RequestDto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.tomcat.jni.Address;

import lombok.Data;

public class HospitalDto {

   
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
