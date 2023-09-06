package com.main.RequestDto;

import javax.validation.constraints.NotBlank;

public class PatientDto {
   
    private UserDto user;
	@NotBlank
    private String patientName;
    private Boolean status;

	public PatientDto() {
		super();
	}

	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
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
