package com.main.entites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


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
