package com.main.RequestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;


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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateOfAppointment;
    @NotNull(message = "Date not be null")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeOfAppointment;

    private Boolean status;


}

