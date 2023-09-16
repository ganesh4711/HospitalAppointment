package com.main.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor@NoArgsConstructor
public class AppointmentListDto
{

    private Long id;
    private Long doctorId;
    private Long patientId;
    private String type;
    private Long receptionStaffId;
    private Date dateOfAppointment;
    private LocalTime timeOfAppointment;
    private Boolean status;


}

