package com.main.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private User user;

    private String doctorName;
    private String type;
    private Boolean status;
    
	public Doctor() {
		super();
	}
	
	public Doctor(Integer doctorId) {
		super();
		this.doctorId = doctorId;
	}

}
