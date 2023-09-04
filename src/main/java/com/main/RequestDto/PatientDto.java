package com.main.RequestDto;
public class PatientDto {

   
    private Integer patientId;

   
    private UserDto user;

    private String patientName;
    private Boolean status;
    
	public PatientDto(Integer patientId) {
		super();
		this.patientId = patientId;
	}
	public PatientDto() {
		super();
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
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
