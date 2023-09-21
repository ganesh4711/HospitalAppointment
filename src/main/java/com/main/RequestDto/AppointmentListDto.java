package com.main.RequestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentListDto {

    private Integer appointmentId;
    @NotNull(message = "doctor id cannot be null")
    private Integer doctorId;
    @NotNull(message = "patient id cannot be null")
    private Integer patientId;
    @NotBlank(message = "type not be null")
    private String type;
    @NotNull(message = "staff id cannot be null")
    private Integer receptionStaffId;

    @NotNull(message = "Date not be null")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dateOfAppointment;
    @NotNull(message = "Date not be null")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeOfAppointment;

    private Boolean status;


}

