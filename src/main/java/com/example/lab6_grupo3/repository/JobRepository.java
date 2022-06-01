package com.example.lab6_grupo3.repository;

import com.example.lab6_grupo3.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
