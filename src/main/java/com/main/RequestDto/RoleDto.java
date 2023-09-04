package com.main.RequestDto;

public class RoleDto {


    private Integer roleId;

    private String roleName;
    private Boolean status;

   

    
    
	public RoleDto() {
		super();
	}

	public RoleDto(Integer roleId2) {
		this.roleId=roleId2;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}



   
}
