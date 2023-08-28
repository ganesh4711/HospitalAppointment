package com.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entites.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
