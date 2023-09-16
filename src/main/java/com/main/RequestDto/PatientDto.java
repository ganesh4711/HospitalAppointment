package com.main.RequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PatientDto {
    private Integer patientId;

    private String patientName;

    private UserDto user;

    private Boolean status;

}
