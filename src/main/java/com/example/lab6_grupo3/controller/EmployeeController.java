package com.example.lab6_grupo3.controller;

import com.example.lab6_grupo3.entity.Employee;
import com.example.lab6_grupo3.repository.DepartamentRepository;
import com.example.lab6_grupo3.repository.EmployeeRepository;
import com.example.lab6_grupo3.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = {"", "/", "/employee"})
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRepository employeesRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    DepartamentRepository departamentRepository;

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
    public List<Employee> getListaJefes() {
        List<Employee> listaJefes = employeesRepository.findAll();
        Employee e = new Employee();
        e.setId(0);
        e.setFirstName("--No tiene Jefe--");
        listaJefes.add(0, e);
        return listaJefes;
    }

    @GetMapping(value ={"/employeeNew"})
    public String nuevoEmpleado(@ModelAttribute("empleado") Employee empleado, Model model){
        model.addAttribute("listaJefes",getListaJefes());
        model.addAttribute("listaTrabajos",jobRepository.findAll());
        model.addAttribute("listaDepartamentos",departamentRepository.findAll());
        return "employee/nuevoempleado";
    }

    @PostMapping(value = {"/employeeSave"})
    public String guardarEmpleado(@ModelAttribute("empleado") @Valid Employee empleado, BindingResult bindingResult, RedirectAttributes attr, Model model){
        if(bindingResult.hasErrors() || employeesRepository.findByEmail((empleado.getEmail())).size()!=0){
            model.addAttribute("listaTrabajos", jobRepository.findAll());
            model.addAttribute("listaJefes", employeesRepository.findAll());
            model.addAttribute("listaDepartamentos",departamentRepository.findAll());
            if(employeesRepository.findByEmail((empleado.getEmail())).size()==0){
                FieldError error = new FieldError("email", "email", "Ya existe un usuario con este correo");
                bindingResult.addError(error);
            }
            return "employee/nuevoempleado";
        }else{
            empleado.setEnabled(1);
            attr.addFlashAttribute("accion","alert-success");
            attr.addFlashAttribute("msg", "Empleado creado exitosamente");
            empleado.setHireDate(new Date());
            employeesRepository.save(empleado);
            return "redirect:/employee/employeeSueldo";
            }
        }
    }

