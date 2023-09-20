package com.example.Employee.MS.repo;

import com.example.Employee.MS.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity,Integer> {
}
