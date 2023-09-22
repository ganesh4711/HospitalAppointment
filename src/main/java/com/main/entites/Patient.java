package com.main.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="patients")
@Data
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private User user;

    private String patientName;
   @Column(columnDefinition = "boolean default 'true' ")
    private Boolean status;
    
	public Patient(Integer patientId) {
		super();
		this.patientId = patientId;
	}
}
