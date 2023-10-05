package com.main.entites;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.main.RequestDto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
    private Integer id;

    private String name;
    private String address;
    private String phNo;
    
    @Column(unique = true)
   
    private String email;
    private String password;
    @Column(columnDefinition = "boolean default 'true'")
    private Boolean status;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<UserRole> userRoles;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private Admin admin;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private ReceptionStaff receptionStaff;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    private Doctor doctor;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JsonIgnore
    private Patient patient;
    

	public User(Integer userId2) {
		this.id=userId2;
	}


    
}
