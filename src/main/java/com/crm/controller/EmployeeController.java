package com.crm.controller;


import com.crm.payload.EmployeeDto;
import com.crm.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<?> createEmployee(
            @Valid @RequestBody EmployeeDto employeeDto,
            BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        EmployeeDto empDto = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(empDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete")
    public String deleteEmployee(@RequestParam long id) {
        employeeService.deleteEmployee(id);
        return "deleted";
    }

    @PutMapping
    public ResponseEntity<EmployeeDto>updateEmployee(

            @RequestParam long id,
            @RequestBody EmployeeDto dto
    ){

        EmployeeDto employeeDto = employeeService.updateEmployee(id,dto);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>>getEmployees(
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo,
            @RequestParam(name = "sortBy", required = false, defaultValue = "0") String sortBy,
            @RequestParam(name = "sortDir", required = false, defaultValue = "asc")String sortDir

    ){
        List<EmployeeDto>employeeDto=employeeService.getEmployees(pageSize,pageNo,sortBy ,sortDir);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);

    }
    @GetMapping("/employeeeId/{empId}")
    public ResponseEntity<EmployeeDto>getEmployeeById(
            @PathVariable long empId){
        EmployeeDto dto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }





}
