package com.main.RequestDto;

import com.main.entites.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	private int userId;

	@NotBlank( message = "user name can't be Blank")
    private String name;
	@NotNull
    private String address;

	@Pattern(regexp = "^\\+\\d{2}[6-9]\\d{9}$")
    private String phNo;
	@NotNull
	@Email(message = "Invalid Email")
    private String email;
	@NotNull
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")
    private String password;

    private Boolean status;

	public UserDto(int userId) {
		this.userId = userId;
	}


}
