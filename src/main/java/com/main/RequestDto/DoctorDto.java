package com.main.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {


    private Integer doctorId;

  
    private UserDto user;

    private String doctorName;
    private String type;

    private Boolean status;
	
	public DoctorDto(Integer doctorId) {
		super();
		this.doctorId = doctorId;
	}


}
