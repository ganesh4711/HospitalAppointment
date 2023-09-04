package com.main.RequestDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class ReceptionStaffDto {

   
    private Integer receptionStaffId;

   
    private UserDto user;

    private String staffName;
    private Boolean status;
    
	public ReceptionStaffDto() {
		super();
	}
	public ReceptionStaffDto(int staffId) {
		this.receptionStaffId=staffId;
	}
	public Integer getReceptionStaffId() {
		return receptionStaffId;
	}
	public void setReceptionStaffId(Integer receptionStaffId) {
		this.receptionStaffId = receptionStaffId;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

    
}
