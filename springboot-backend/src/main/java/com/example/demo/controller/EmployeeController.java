package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	
	private EmployeeService employeeservice;
		
	public EmployeeController(EmployeeService employeeservice) {
		super();
		this.employeeservice=employeeservice;
	}
	//build to create employee REST api
		@PostMapping
		public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
			//employeeservice.saveEmployee(employee);
			//return employee;
			
			ResponseEntity<Employee> re=new ResponseEntity<Employee>(employeeservice.saveEmployee(employee), HttpStatus.CREATED);
		return re;
	}	
		//build to get all employees Rest Api
		@GetMapping
		public List<Employee> getEmployee(){
			return employeeservice.getEmployee();
		}
		
	//build to get Employee by id
		//hhtp://localhost:8080/api/employees/17
		@GetMapping("{id}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
			
			return new ResponseEntity<Employee>(employeeservice.getEmployeeById(employeeId), HttpStatus.OK);
			
		}
		
		//build update employee by rest api
		
		@PutMapping("{id}")
	public 	ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") long employeeId){
		
		return new ResponseEntity<Employee> (employeeservice.updateEmployee(employee, employeeId),HttpStatus.OK); 	
	}
		
		@DeleteMapping("{id}")
		
		public 	ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
			
			return new ResponseEntity<String>("Employee deleted sucessfully", HttpStatus.OK);
		}
		
		
		
		
		
		
}
