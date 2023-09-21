package com.main.RequestDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
