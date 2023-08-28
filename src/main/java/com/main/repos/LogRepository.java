package com.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entites.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    // Define any custom query methods if needed
}
