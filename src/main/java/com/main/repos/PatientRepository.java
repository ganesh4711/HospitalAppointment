package com.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entites.Appointment;
import com.main.entites.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Patient findByUser_UserId(int uid);
}
