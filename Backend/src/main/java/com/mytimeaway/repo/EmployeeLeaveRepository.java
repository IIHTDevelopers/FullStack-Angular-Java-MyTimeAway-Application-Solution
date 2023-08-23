package com.mytimeaway.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mytimeaway.entity.EmployeeLeave;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long> {
}
