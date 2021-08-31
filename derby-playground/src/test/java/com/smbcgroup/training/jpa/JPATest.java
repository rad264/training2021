package com.smbcgroup.training.jpa;

import static org.junit.Assert.assertEquals;

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
		em.persist(new Employee(1452,  "Alison Smith"));
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
