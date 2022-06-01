package com.example.lab6_grupo3.entity;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    private Integer id;

    @Column(name = "department_name", nullable = false, length = 30)
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

}