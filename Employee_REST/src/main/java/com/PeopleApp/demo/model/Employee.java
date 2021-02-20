package com.PeopleApp.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	private String name;
	private String CInfo;
	private int age;
	private String state;

/*	public Employee(String name,  int age, String state,String cInfo) {
		super();
		this.name = name;
		this.CInfo = cInfo;
		this.age = age;
		this.state=state;
	}*/
	public String getName() {

		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCInfo() {
		return CInfo;
	}
	public void setCInfo(String cInfo) {
		this.CInfo = cInfo;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
/*	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	*/
	public Estate getEState() 
	{
		return Estate.valueOf(this.state);
	}
	public void setEState(Estate st) 
	{
		this.state = st.name();
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", CInfo=" + CInfo + ", age=" + age + ", state=" + state + "]";
	}

}
