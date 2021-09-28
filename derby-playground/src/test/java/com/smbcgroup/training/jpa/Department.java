package com.smbcgroup.training.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Departments")
public class Department {

	@Id
	@Column
	private Integer id;
	
	@Column (name = "department_name")
	private String departmentName;
	
	@Column (name = "manager_name")
	private String managerName;
	
	//@OneToMany (mappedBy = "department", cascade = CascadeType.PERSIST)
	//private List<Employee> employees;
	
	public Department() {
		
	}
	
	public Department(Integer id, String departmentName, String managerName) {
		this.id = id;
		this.departmentName = departmentName;
		this.managerName = managerName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getManagerName() {
		return managerName;
	}
	
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
}
