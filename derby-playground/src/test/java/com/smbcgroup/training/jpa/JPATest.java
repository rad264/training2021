package com.smbcgroup.training.jpa;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JPATest {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("derby-entities");
	private EntityManager em = emf.createEntityManager();
	
	@Before
	public void setup() {
		em.getTransaction().begin();
		populateTables();
		em.flush();
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	private void populateTables() {
		Department treasuryDept = new Department(1, "Treasury", "Alex Smith");
		Department loansDept = new Department(2, "Loans", "Alex Smith");
		Department jriDept = new Department(3, "JRI", "Beatriz Smith");
		em.persist(treasuryDept);
		em.persist(loansDept);
		em.persist(jriDept);
		
		em.persist(new Employee(1452, "Alison Smith", BigDecimal.valueOf(70000), treasuryDept));
		em.persist(new Employee(1453, "Bob Smith", BigDecimal.valueOf(60000), treasuryDept));
		em.persist(new Employee(1454, "Chris Smith", BigDecimal.valueOf(50000), treasuryDept));
		em.persist(new Employee(1455, "Dinesh Smith", BigDecimal.valueOf(50000), loansDept));
		em.persist(new Employee(1456, "Emily Smith", BigDecimal.valueOf(90000), jriDept));
		em.persist(new Employee(1457, "Felix Smith", BigDecimal.valueOf(80000), jriDept));
		em.persist(new Employee(1458, "Gonzo Smith", BigDecimal.valueOf(100000), jriDept));
	}
	 
	@Test
	public void testFind() {
		Employee employee = em.find(Employee.class, 1454);
		assertEquals("Chris Smith", employee.getName());
	}
	
	@Test
	public void testHighestToLowestWage() {
		List<Employee> employees = em.createQuery("SELECT e FROM Employee e WHERE e.wage > 50000 ORDER BY e.wage DESC", Employee.class).getResultList();
		assertEquals(5, employees.size());
	}
	
	@Test
	public void testTreasuryEmployees() {
		List<Employee> numEmployees = em.createQuery("SELECT e FROM Employee e JOIN e.department d WHERE d.name = 'Treasury'", Employee.class).getResultList();
		assertEquals(3, numEmployees.size());
	}
	
	@After
	public void teardown() {
		em.getTransaction().rollback();
		em.close();
	}

}
