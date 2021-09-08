package com.smbcgroup.training.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Departments")
public class Department {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@Column(name = "manager_name")
	private String managerName;
	
	@OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
	private List<Employee> employees;
	
	public Department() {
		
	}
	
	public Department(Integer id, String name, String managerName) {
		this.id = id;
		this.name = name;
		this.managerName = managerName;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getManagerName() {
		return managerName;
	}
	
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
//	public List<Employee> getEmployees() {
//		return employees;
//	}
//	
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}
//	
//	public void addEmployee(Employee employee) {
//		this.employees.add(employee);
//	}
}
