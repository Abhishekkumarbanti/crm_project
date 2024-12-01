package com.crm.service;


import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.PageRequest.of;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDto mapToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public Employee MapToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = MapToEntity(employeeDto);
        Employee empData = employeeRepository.save(employee);
        return mapToDto(empData);
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(long id, EmployeeDto dto) {
        Employee employee = MapToEntity(dto);
        employee.setId(id);
        Employee updateEmployee = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updateEmployee);
        return employeeDto;
    }

//    public List<EmployeeDto> getEmployees(){
//        List<Employee>employee=employeeRepository.findAll();
//        List<EmployeeDto>dto=employee.stream().map(e->mapToDto(e)).
//                collect(Collectors.toList());
//        return dto;
//
//    }

    public EmployeeDto getEmployeeById(long empId) {
        // Optional<Employee> opEmp = employeeRepository.findById(empId);
        // Employee employee = opEmp.get();
        //return mapToDto(employee);
        Employee employee = employeeRepository.findById(empId).orElseThrow(
                () -> new ResourceNotFound("Record Not found!")
        );
        return mapToDto(employee);
    }

    public List<EmployeeDto> getEmployees(int pageSize, int pageNo, String sortBy , String sortDir ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable page =  PageRequest.of(pageNo,pageSize,sort);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDto> dtos = employees.stream().map(emp -> mapToDto(emp)).collect(Collectors.toList());
        return dtos;
    }
}



