package com.aaslin.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aaslin.orm_mar14.Department;
import com.aaslin.orm_mar14.Employee;

public class DataBase {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	List<Employee> obj = new ArrayList<Employee>();

	public boolean openDBConn() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			st = con.createStatement();
			return true;
		} catch (ClassNotFoundException cnfe) {
			System.err.println("JDBC driver not found");
		} catch (SQLException se) {
			System.err.println("JDBC connection  failed. Message : " + se.getMessage());
		} catch (Exception e) {
			System.err.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public void closeDBConn() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.err.println("JDBC connection close failed. Message : " + e.getMessage());
		}
	}

	public List<Employee> queryExecute(String query) throws SQLException {
		rs = st.executeQuery(query);
		obj.clear();
		while (rs.next()) {
			Employee e = new Employee();
			Department d = new Department();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			d.setId(rs.getInt(3));
			d.setName(rs.getString(4));
			e.setDept(d);
			obj.add(e);
			e = null;
			d = null;
		}
		return obj;
	}
public  void createEmployee(String name,String deptName) throws SQLException {
		
		String query = "insert into employee1(name,deptid) values(\""+name+"\",(select id from department1 where name=\""+deptName+"\"))";
		int value = queryUpdate(query);
		if(value>0) {
			System.out.println("employee creation success");
		}
		else
			System.out.println("record not inserted");
	}

	public  int queryUpdate(String query) throws SQLException {
		return st.executeUpdate(query);
	}

	public  void removeEmployee(int id) throws SQLException {
		String query="delete from employee1 where id="+id;
		int i=st.executeUpdate(query);
		if(i==1) {
			System.out.println("user deleted successfully");
		}
		else
			System.out.println("no user to delete");
	}

	public void updateEmployee(int id,String name) throws SQLException {
		String query="update employee1 set name=\""+name+"\" where id="+id;
		int i=st.executeUpdate(query);
		if(i==1) {
			System.out.println("user updated successfully");
		}
		else
			System.out.println("no user to update");
	}
}
