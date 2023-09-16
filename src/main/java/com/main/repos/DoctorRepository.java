package com.main.repos;

import com.main.RequestDto.DoctorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entites.Doctor;
import com.main.entites.Patient;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	Doctor findByUser_Id(int uid);
    List<DoctorDto> findAllByType(String type);
}
