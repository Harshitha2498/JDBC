package com.aaslin.connections;

public class Employee {
	private int id;
	private String name;
	private Department department;
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return "[Employee id=" + this.id + ", name=" + this.name + ", " + department.toString() + "]";
	}
}
