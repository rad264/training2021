package com.smbcgroup.training.jpa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
public class Employee {
	
	@Id
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@Column (name = "department_id")
	private Integer departmentId;
	
	@Column (precision = 10, scale = 2)
	private BigDecimal wage;
	
	/*
	 * private Department department;
	
	@ManyToOne
	public Department getDepartment() {
       return department;
   }
	
	public void setDepartment(Department department) {
        this.department = department;
    }
	 */
	
	
	public Employee() {
		
	}

	public Employee(Integer id, String name, BigDecimal wage, Integer departmentId) {
		this.id = id;
		this.name = name;
		this.wage = wage;
		this.departmentId = departmentId;
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
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	public BigDecimal getWage() {
		return wage;
	}
	
	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}

}
