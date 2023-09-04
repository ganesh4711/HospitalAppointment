package com.main.RequestDto;


public class AdminDto {

 
    private Integer adminId;

    //private UserDto user;

    private String adminName;
    private Boolean status;
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
//	public UserDto getUser() {
//		return user;
//	}
//	public void setUser(UserDto user) {
//		this.user = user;
//	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

   
}
