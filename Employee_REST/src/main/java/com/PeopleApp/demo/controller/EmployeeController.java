package com.PeopleApp.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.PeopleApp.demo.dao.EmployeeRepo;
import com.PeopleApp.demo.model.Employee;
import com.PeopleApp.demo.model.EnumStateMachineConfiguration;
import com.PeopleApp.demo.model.Estate;


@RestController
public class EmployeeController {
	
	private final com.PeopleApp.demo.controller.KafkaProducer producer;
	public EmployeeController(KafkaProducer producer)
	{
		this.producer=producer;
	}

	@PutMapping(path="/ChangeINCHECK/{name}", consumes= {"application/json"})
	public void writeModifyByNameToTopic(@PathVariable("name") String name)
	{
		this.producer.writeModifyByNameMessage(name);
	}
	
	@PutMapping(path="/ChangeINCHECK", consumes= {"application/json"})
	public void writeModifyToTopic(@RequestBody String message)
	{
		this.producer.writeModifyMessage(message);
	}
	
	@PostMapping(path="/publish", consumes= {"application/json"})
	public void writeMessageToTopic(@RequestBody String message) 
	{
		this.producer.writeMessage(message);
	}
	
	@Autowired
	EmployeeRepo repo;
	@RequestMapping("/")
	public String home()
	{
		return "home.jsp";
		//System.out.println("Hello");
	}
	
	@DeleteMapping(path="/Employee/{name}", consumes= {"application/json"})
	public String  deleteEmployee(@PathVariable("name") String name)
	{
		Employee Emp=repo.getOne(name);
		repo.delete(Emp);
		return "Deleted";
	}
	
	@PutMapping(path="/Employee", consumes= {"application/json"})
	public Employee  saveOrUpdateEmployee(@RequestBody Employee Emp)
	{	
		Emp.setEState(Estate.ADDED);
		repo.save(Emp);
		return Emp;
	}
	
	@PostMapping(path="/EmployeeModify", consumes= {"application/json"})
	public Employee  modifyEmployee(@RequestBody Employee Emp)
	{
		Emp.setEState(Estate.INCHECK);
		repo.save(Emp);
		return Emp;
	}

	@PostMapping(path="/Employee", consumes= {"application/json"})
	public Employee  addEmployee(@RequestBody Employee Emp)
	{
		Emp.setEState(Estate.ADDED);
		repo.save(Emp);
		return Emp;
	}
	@GetMapping("/Employees")
	@ResponseBody
	public List<Employee> getEmployees()
	{
		return repo.findAll();
	}
	@RequestMapping("/Employee/{name}")
	@ResponseBody
	public Optional<Employee> getEmployee(@PathVariable("name") String name)
	{
		return repo.findById(name);
	}
}
