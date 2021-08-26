package com.smbcgroup.training.atm.dao.jpa;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

public class InitDB {

	public static void main(String[] args) {
		EntityManager em = new AccountJPAImpl().emf.createEntityManager();
		em.getTransaction().begin();

		UserEntity rdelaney = new UserEntity("rdelaney");
		em.persist(rdelaney);
		em.merge(new AccountEntity("123456", new BigDecimal("100"), rdelaney));

		em.getTransaction().commit();
		em.close();
	}

}
