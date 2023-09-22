package com.main.RequestDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ReceptionStaffDto {

   
    private Integer receptionStaffId;

   @NotNull(message = "user id not be null")
    private UserDto user;

    private String staffName;
    private Boolean status;

	public ReceptionStaffDto(int staffId) {
		this.receptionStaffId=staffId;
	}

}
