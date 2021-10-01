package com.smbcgroup.training.atm.dao.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.Transaction;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.TransactionNotFoundException;
import com.smbcgroup.training.atm.dao.UserAlreadyExistException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;

public class AccountJPAImpl implements AccountDAO {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("derby-entities");

	@Override
	public User getUser(String userId) throws UserNotFoundException {
		EntityManager em = emf.createEntityManager();
		try {
			UserEntity entity = em.find(UserEntity.class, userId);
			if (entity == null)
				throw new UserNotFoundException();
			return entity.convertToUser();
		} finally {
			em.close();
		}
	}

	@Override
	public Account getAccount(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		try {
			AccountEntity entity = em.find(AccountEntity.class, accountNumber);
			if (entity == null)
				throw new AccountNotFoundException();
			return entity.convertToAccount();
		} finally {
			em.close();
		}
	}

	@Override
	public void updateAccount(Account account) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			AccountEntity entity = new AccountEntity();
			entity.setAccountNumber(account.getAccountNumber());
			entity.setBalance(account.getBalance());
			em.merge(entity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public Account createAccount(String userId, String accountNumber) throws UserNotFoundException {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			UserEntity userEntity = em.find(UserEntity.class, userId);
			if (userEntity == null)
				throw new UserNotFoundException();
			AccountEntity entity = new AccountEntity();
			entity.setAccountNumber(accountNumber);
			entity.setBalance(BigDecimal.ZERO);
			entity.setUser(userEntity);
			em.persist(entity);
			em.getTransaction().commit();
			return entity.convertToAccount();
		} finally {
			em.close();
		}
		
	}
	
	@Override
	public User createUser(String userId) throws UserAlreadyExistException {
		EntityManager em = emf.createEntityManager();
		try {
			UserEntity userEntity = em.find(UserEntity.class, userId);
			if (userEntity != null)
				throw new UserAlreadyExistException();
			em.getTransaction().begin();
			UserEntity entity = new UserEntity();
			entity.setId(userId);
			List<AccountEntity> accounts = new ArrayList<>();
			entity.setAccounts(accounts);
			em.persist(entity);
			em.getTransaction().commit();
			return entity.convertToUser();
		} finally {
			em.close();
		}
	}
	
	@Override
	public void createTransaction(String accountNumber, BigDecimal amount, String type, String description) throws AccountNotFoundException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			AccountEntity accountEntity = em.find(AccountEntity.class, accountNumber);
			if (accountEntity == null) {
				throw new AccountNotFoundException();
			}
			TransactionEntity entity = new TransactionEntity();
			entity.setAccount(accountEntity);
			entity.setAmount(amount);
			entity.setCreatedTime(new Date());
			entity.setType(type);
			entity.setDescription(description);
			em.persist(entity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public Transaction getTransaction(long transactionId) throws TransactionNotFoundException {
		EntityManager em = emf.createEntityManager();
		try {
			TransactionEntity entity = em.find(TransactionEntity.class, transactionId);
			if (entity == null)
				throw new TransactionNotFoundException();
			return entity.convertToTransaction();
		} finally {
			em.close();
		}
	}
	
	@Override
	public Transaction[] getAllTransactionsByAccount(String accountNumber) throws AccountNotFoundException {
		EntityManager em = emf.createEntityManager();
		
		try {
			List<TransactionEntity> transactions = em.createQuery("SELECT t FROM TransactionEntity t JOIN t.account a WHERE a.accountNumber = '" + accountNumber + "'").getResultList();
			return transactions.stream().map(TransactionEntity::convertToTransaction).toArray(Transaction[]::new);
		} finally {
			em.close();
		}
	}
}
