package com.main.RequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PatientDto {
    private Integer patientId;
    @NotBlank
    private String patientName;
    private UserDto user;

    private Boolean status;

}
