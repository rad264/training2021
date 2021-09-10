package com.smbcgroup.training.jpa;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
		em.persist(new Employee(1452, "Alison Smith", new  BigDecimal(70000), 1));
		em.persist(new Employee(1453, "Bob Smith", new  BigDecimal(60000), 1));
		em.persist(new Employee(1454, "Chris Smith", new  BigDecimal(50000), 1));
		em.persist(new Employee(1455, "Dinesh Smith", new  BigDecimal(50000), 2));
		em.persist(new Employee(1456, "Emily Smith", new  BigDecimal(90000), 3));
		em.persist(new Employee(1457, "Felix Smith", new  BigDecimal(80000), 3));
		em.persist(new Employee(1458, "Gonzo Smith", new  BigDecimal(100000), 3));
		
		
		em.persist(new Department(1, "Treasury", "Alex Smith"));
		em.persist(new Department(2, "Loans", "Alex Smith"));
		em.persist(new Department(1, "JRIA", "Beatriz Smith"));
		
		
	}
	
	@Test
	public void testFind() {
		Employee employee = em.find(Employee.class, 1452);
		assertEquals("Alison Smith", employee.getName());
	}
	
	@After
	public void teardown() {
		em.getTransaction().rollback();
		em.close();
	}

}
