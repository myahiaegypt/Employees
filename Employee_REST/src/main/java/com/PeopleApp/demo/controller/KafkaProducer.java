package com.PeopleApp.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.PeopleApp.demo.model.Employee;

@Service
public class KafkaProducer {
	private static final String TOPIC="AddEmployee";
	private static final String MODIFYTOPIC="ModifyEmployee";
	private static final String MODIFYBYNAMETOPIC="ModifyByNameEmployee";
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	public void writeMessage(String msg) 
	{
		this.kafkaTemplate.send(TOPIC, msg);
	}
	
	public void writeModifyMessage(String msg) 
	{
		this.kafkaTemplate.send(MODIFYTOPIC, msg);
	}
	
	public void writeModifyByNameMessage(String msg) 
	{
		this.kafkaTemplate.send(MODIFYBYNAMETOPIC, msg);
	}	
	
}
