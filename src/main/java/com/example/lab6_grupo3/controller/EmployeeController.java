package com.example.lab6_grupo3.controller;

import com.example.lab6_grupo3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
@RequestMapping(value = {"", "/", "/employee"})
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping(value = {"/employeeSueldo"})
    public String listaEmployeeSueldo(Model model, @RequestParam(value = "sueldo",required = false) Double monto) {

        BigDecimal montoDecimal = BigDecimal.valueOf(0);
        if (monto!=null && monto > 0) {
            montoDecimal = BigDecimal.valueOf(monto);
        }
        model.addAttribute("listaEmployeeSueldo", employeeRepository.findEmployeeSueldo(montoDecimal));
        model.addAttribute("monto",montoDecimal);
        return "employee/employeesueldo";
    }


}
