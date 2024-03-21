package com.aaslin.connections;

import java.sql.SQLException;
import java.util.List;

import com.aaslin.orm_mar14.Employee;

public class Program {
	static DataBase db;

	public static void main(String[] args) throws SQLException {
		db = new DataBase();
		if (db.openDBConn()) {
			printEmployeeList();
			db.createEmployee("kaju","IT");
			printEmployeeList();
			db.removeEmployee(8);
			printEmployeeList();
			db.updateEmployee(10,"mahi");
			printEmployeeList();
			db.closeDBConn();
		} else
			System.out.println("connection failed");
	}

	private static void printEmployeeList() throws SQLException {
		String query = "select e.id,e.name,d.id,d.name from employee1 e inner join department1 d where e.deptId=d.id";
		List<Employee> resultset = db.queryExecute(query);
		for (Employee e : resultset) {
			System.out.println(e.toString());
		}
	}
}
