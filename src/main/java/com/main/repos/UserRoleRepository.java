package com.main.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entites.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

	List<UserRole> findByRole_RoleName(String role);

	boolean existsByRole_RoleName(String role);
}
