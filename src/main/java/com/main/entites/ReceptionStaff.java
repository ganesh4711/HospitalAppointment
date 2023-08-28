package com.main.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ReceptionStaff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receptionStaffId;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId", unique = true)
    private User user;

    private String staffName;
    private Boolean status;
	public Integer getReceptionStaffId() {
		return receptionStaffId;
	}
	public void setReceptionStaffId(Integer receptionStaffId) {
		this.receptionStaffId = receptionStaffId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

    
}
