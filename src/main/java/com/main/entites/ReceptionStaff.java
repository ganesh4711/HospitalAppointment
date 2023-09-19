package com.main.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
public class ReceptionStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receptionStaffId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private User user;

    private String staffName;
    private Boolean status;

	public ReceptionStaff(int staffId) {
		this.receptionStaffId=staffId;
	}

    
}
