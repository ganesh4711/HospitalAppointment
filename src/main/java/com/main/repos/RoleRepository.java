package com.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entites.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
