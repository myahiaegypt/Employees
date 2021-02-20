package com.PeopleApp.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.PeopleApp.demo.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, String>
{

}
