package com.main.RequestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDto {

 
    private Integer adminId;
	@NotNull(message = "user id not be null")
    private Integer userId;

    private String adminName;
    private Boolean status;


   
}
