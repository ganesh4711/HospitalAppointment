package com.main.RequestDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDto {

    private Integer appointmentId;

	@NotNull
    private Integer doctorId;

   @NotNull
    private Integer patientId;
   @NotBlank
   private String type;
	@NotNull
    private Integer staffId;
	@NotNull
    private LocalDate dateOfAppointment;
	@NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime timeOfAppointment;
    private Boolean status;
	
}
