package com.PeopleApp.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.PeopleApp.demo.dao.EmployeeRepo;
import com.PeopleApp.demo.model.Employee;
import com.PeopleApp.demo.model.Estate;
import java.util.Optional;

@Service
public class KafkaModifyByNameConsumer {
	@Autowired
	EmployeeRepo repo;
	@KafkaListener(topics="ModifyByNameEmployee", groupId="my_group_id")
	public void consumerModifyByName (String message)
	{
			Optional<Employee> Emp1=repo.findById(message);
		    Employee Emp = Emp1.get();
			Emp.setEState(Estate.INCHECK);
			repo.save(Emp);
	}
}
