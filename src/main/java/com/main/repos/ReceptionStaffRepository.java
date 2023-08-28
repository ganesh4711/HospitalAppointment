package com.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entites.ReceptionStaff;

@Repository
public interface ReceptionStaffRepository extends JpaRepository<ReceptionStaff, Integer> {
}
