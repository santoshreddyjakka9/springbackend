package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		
		super();
		this.employeeRepository=employeeRepository;
	}
	
	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee) ;
		
	}
	
	public List<Employee> getEmployee(){
		return employeeRepository.findAll();
	}
	
	@Override
	public Employee getEmployeeById(long id) {
		//Optional<Employee> employee=employeeRepository.findById(id);
		
		//if(employee.isPresent()) {
			//return employee.get();
		//}else {
		//	throw new ResourceNotFoundException("Employee", "Id", id);
	//	}
		
		return employeeRepository.findById(id).orElseThrow(() ->
			new ResourceNotFoundException("Employee", "Id", id));
}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//we need to check whether employee with given id is exist in DB or not
		Employee exisitngEmployee = employeeRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Employee", "Id", id));
		
		exisitngEmployee.setFirstName(employee.getFirstName());
		exisitngEmployee.setLastName(employee.getLastName());
		exisitngEmployee.setEmail(employee.getEmail());
		
		// save existing data 
		employeeRepository.save(exisitngEmployee);
		
		return exisitngEmployee;
	}
	
	public void deleteEmployee(long id) {
		/*
		 * Optional<Employee> employee=employeeRepository.findById(id);
		 * 
		 * if(employee.isPresent()) { employeeRepository.deleteById(id); }else { throw
		 * new ResourceNotFoundException("Employee", "Id", id); }
		 */
		 employeeRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Employee", "Id", id));
		 
		 employeeRepository.deleteById(id);
		
	}
	
	
	
}
