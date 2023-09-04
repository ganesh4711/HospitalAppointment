package com.main.RequestDto;

public class UserRoleDto {

  
    private Integer userRoleId;


    private RoleDto role;

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}


	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	} 
}
