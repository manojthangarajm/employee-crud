package com.example.jenkinspractice.service;

import com.example.jenkinspractice.entity.Employee;
import com.example.jenkinspractice.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee save(Employee emp) {
        return repository.save(emp);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee update(Long id, Employee emp) {
        Employee existing = getById(id);
        existing.setName(emp.getName());
        existing.setRole(emp.getRole());
        existing.setSalary(emp.getSalary());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}