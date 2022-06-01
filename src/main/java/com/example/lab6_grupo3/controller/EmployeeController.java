package com.example.lab6_grupo3.controller;

import com.example.lab6_grupo3.entity.Employee;
import com.example.lab6_grupo3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"", "/", "/employee"})
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = {"","/employeeSueldo"})
    public String listaEmployeeSueldo(Model model, @RequestParam(value = "sueldo",required = false) Double monto) {

        BigDecimal montoDecimal = BigDecimal.valueOf(0);
        if (monto!=null && monto > 0) {
            montoDecimal = BigDecimal.valueOf(monto);
        }
        model.addAttribute("listaEmployeeSueldo", employeeRepository.findEmployeeSueldo(montoDecimal));
        model.addAttribute("monto",montoDecimal);
        return "employee/employeesueldo";
    }

    @GetMapping(value = {"/emplExperiencia"})
    public String empleadoExperiencia(Model model){
        model.addAttribute("listEmpleadoExperiencia",employeeRepository.listarExmpleadoExperiencia());
        return "employee/empleadoExperiencia";
    }

    @GetMapping("/lista")
    public String listaGeneral(Model model){
        model.addAttribute("listaGeneral",employeeRepository.findAll());
        return "employee/lista";
    }

    @GetMapping("/renta")
    public String renta(Model model,@RequestParam("id") Integer id){
        Optional<Employee> optionalEmp= employeeRepository.findById(id);
        if (optionalEmp.isPresent()) {
            Employee employee = optionalEmp.get();
            model.addAttribute("empleado", employee);
            model.addAttribute("renta",employeeRepository.findEmployeeRenta(id));
            return "employee/renta";
        } else {
            return "redirect:/lista";
        }
    }




}
