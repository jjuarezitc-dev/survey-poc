package com.example.application.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.data.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
