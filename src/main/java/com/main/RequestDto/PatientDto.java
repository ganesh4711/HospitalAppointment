package com.main.RequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PatientDto {
    private Integer patientId;

    private String patientName;

    private UserDto user;

    private Boolean status;

}
