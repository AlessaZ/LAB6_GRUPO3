package com.example.lab6_grupo3.repository;

import com.example.lab6_grupo3.dto.EmployeeSueldoDto;
import com.example.lab6_grupo3.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value = "CALL listaEmpleados(:sueldo);", nativeQuery = true)
    List<EmployeeSueldoDto> findEmployeeSueldo (@Param("sueldo") BigDecimal sueldo);

    List<Employee> findByEmail(String email);
}
