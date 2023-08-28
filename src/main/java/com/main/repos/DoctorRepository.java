package com.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entites.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
