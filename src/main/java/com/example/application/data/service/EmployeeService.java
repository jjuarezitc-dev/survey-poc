package com.example.application.data.service;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.data.entity.Beverage;
import com.example.application.data.entity.Department;
import com.example.application.data.entity.Employee;
import com.example.application.data.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired EmployeeRepository employeeRepository;
	
	public void save(final String name,
					 final String lastName,
					 final String email,
					 final LocalDate birthDate,
					 final Department department,
					 final Set<Beverage> favoriteBeverages,
					 final String favoritePet) {
		
		Employee employee = new Employee(name, lastName, email,
				java.sql.Date.valueOf(birthDate), department, favoriteBeverages, favoritePet);
		employeeRepository.save(employee);
	}
}
