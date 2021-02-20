package com.PeopleApp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.PeopleApp.demo.dao.EmployeeRepo;
import com.PeopleApp.demo.model.Employee;
import com.PeopleApp.demo.model.Estate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KafkaConsumer {

	@Autowired
	EmployeeRepo repo;
	@KafkaListener(topics="AddEmployee", groupId="my_group_id")
	public void consumer(String message) 
	{
		//System.out.println(message);
		/*Emp.setEState(Estate.ADDED);
		//repo.save(Emp);
		//return Emp; */
		ObjectMapper mapper=new ObjectMapper();
		TypeReference<Employee> typeRef = new TypeReference<Employee>() {};
		//InputStream inputStream= TypeReference.class.getResourceStream
		try
		{
			Employee Emp=mapper.readValue(message, typeRef);
			Emp.setEState(Estate.ADDED);
			repo.save(Emp);
		}
		catch (IOException io)
		{
			System.out.println("can't add employee");
		}
	}

}
