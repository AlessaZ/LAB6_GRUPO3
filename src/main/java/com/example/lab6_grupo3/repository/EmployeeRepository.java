package com.example.lab6_grupo3.repository;

import com.example.lab6_grupo3.dto.empleadoExperienciaDto;
import com.example.lab6_grupo3.dto.EmployeeSueldoDto;
import com.example.lab6_grupo3.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {


    @Query(value = "call empleadosAnios()", nativeQuery = true)
    List<empleadoExperienciaDto> listarExmpleadoExperiencia();
    @Query(value = "CALL listaEmpleados(:sueldo);", nativeQuery = true)
    List<EmployeeSueldoDto> findEmployeeSueldo (@Param("sueldo") BigDecimal sueldo);
}
