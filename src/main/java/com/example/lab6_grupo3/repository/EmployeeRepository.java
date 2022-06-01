package com.example.lab6_grupo3.repository;

import com.example.lab6_grupo3.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


}
