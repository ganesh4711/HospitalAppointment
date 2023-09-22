package com.main.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
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
