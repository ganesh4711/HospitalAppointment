package com.main.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@NotBlank( message = "user name can't be Blank")
    private String name;
	@NotNull
    private String address;
	@Pattern(regexp = "^\\+\\d{2}[6-9]\\d{9}$")
    private String phNo;
	@Email(message = "Invalid Email")
    private String email;
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")
    private String password;

    private Boolean status;

	public UserDto(Integer userId, String name) {
	}


}
