package com.main.RequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReceptionStaffDto {

   
    private Integer receptionStaffId;

   
    private UserDto user;

    private String staffName;
    private Boolean status;

	public ReceptionStaffDto(int staffId) {
		this.receptionStaffId=staffId;
	}


    
}
