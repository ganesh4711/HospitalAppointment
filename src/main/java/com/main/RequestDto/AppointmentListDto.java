package com.main.RequestDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor@NoArgsConstructor
public class AppointmentListDto
{

    private Long id;
    @NotBlank(message = "doctor id cannot be blank")
    private Integer doctorId;
    @NotBlank(message = "patient id cannot be blank")
    private Integer  patientId;
    @NotNull(message = "type not be null")
    private String type;
    @NotBlank(message = "staff id cannot be blank")
    private Long receptionStaffId;

    @NotNull(message = "Date not be null")
    @DateTimeFormat(pattern =  "MM/dd/yyyy")
    private LocalDate dateOfAppointment;
    @NotNull(message = "Date not be null")
    @DateTimeFormat(pattern =  "HH:mm")
    private LocalTime timeOfAppointment;
    private Boolean status;


}

