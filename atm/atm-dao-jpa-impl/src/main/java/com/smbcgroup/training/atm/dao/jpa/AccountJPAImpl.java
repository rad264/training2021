package com.smbcgroup.training.atm.dao.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.NonUniqueIdException;
import com.smbcgroup.training.atm.User;
import com.smbcgroup.training.atm.dao.AccountDAO;
import com.smbcgroup.training.atm.dao.AccountNotFoundException;
import com.smbcgroup.training.atm.dao.UserNotFoundException;
import java.util.List;

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
		EntityManager em = emf.createEntityManager();
		try {
			AccountEntity entity = em.find(AccountEntity.class, accountNumber);
			if (entity == null) {
				throw new AccountNotFoundException();
			}
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
	public void createAccount(String userId, String accountNumber, BigDecimal balance) throws UserNotFoundException {
		//for existing user
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		try {
			User user = getUser(userId);
			String[] accounts = user.getAccounts();
			String[] updatedAccountsList = new String[accounts.length + 1];
			for(int i = 0; i < accounts.length; i++) {
				updatedAccountsList[i] = accounts[i];
			}
			updatedAccountsList[accounts.length] = accountNumber;
			user.setAccounts(updatedAccountsList);
			
			Account account = new Account();
			account.setAccountNumber(accountNumber);
			account.setBalance(balance);
			
			AccountEntity entity = new AccountEntity();
			entity.setAccountNumber(account.getAccountNumber());
			entity.setBalance(account.getBalance());
			
			UserEntity entity2 = em.find(UserEntity.class, userId);
			entity.setUser(entity2);
			
			
			em.merge(entity);
			em.merge(entity2);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}
	
	@Override
	public void createUser(String newUserId) throws NonUniqueIdException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("Users.findAll");
		List allUsers = query.getResultList();
		boolean isNewIdUnique = true;
		for (int i = 0; i < allUsers.size(); i ++) {
			if (newUserId.equals(allUsers.get(i))) {
				isNewIdUnique = false;
				break;
			}
		}
		
		if (isNewIdUnique == true) {
			UserEntity newUser = new UserEntity(newUserId);
			em.merge(newUser);
			em.getTransaction().commit();
			em.close();
		} else {
			throw new NonUniqueIdException("This user ID is already taken. Please choose a different user ID.");
		}

	}

	

	@Override
	public void updateTransactionHistory(String userId, String message) throws UserNotFoundException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			UserEntity userEntity = em.find(UserEntity.class, userId);
			if (userEntity == null) {
				throw new UserNotFoundException();
			}
			TransactionEntity transactionEntity = new TransactionEntity();
			transactionEntity.setUser(userEntity);
			transactionEntity.setTransaction(message);
			em.merge(transactionEntity);
			em.getTransaction().commit();
		} finally {
			em.close();
		}
	}

	@Override
	public ArrayList<String> getTransactionHistory(String userId) throws UserNotFoundException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			UserEntity userEntity = em.find(UserEntity.class, userId);
			if (userEntity == null) {
				throw new UserNotFoundException();
			}
			List<TransactionEntity> transactions = userEntity.getTransactions();
			ArrayList<String> transactionList = new ArrayList<String>();
			for (int i = 0; i < transactions.size(); i++) {
				transactionList.add(transactions.get(i).getTransaction());
			}
			em.merge(userEntity);
			em.getTransaction().commit();
			return transactionList;
		} finally {
			em.close();
		}
		
	}
	
	//---MAIN METHOD TO TEST----
	public static void main(String[] args) throws Exception {
		AccountJPAImpl dao = new AccountJPAImpl();

		
		EntityManager em = dao.emf.createEntityManager();
		em.getTransaction().begin();

		/*
		 * UserEntity rdelaney = new UserEntity("rdelaney"); em.merge(rdelaney);
		 * AccountEntity accountEntity = new AccountEntity("123456", new
		 * BigDecimal("100"), rdelaney); em.merge(accountEntity); AccountEntity
		 * accountEntity2 = new AccountEntity("222333", new BigDecimal("500"),
		 * rdelaney); em.merge(accountEntity2); TransactionEntity trans1 = new
		 * TransactionEntity(1, "Deposited $10 to 123456", rdelaney); em.merge(trans1);
		 * TransactionEntity trans2 = new TransactionEntity(3,
		 * "Withdrew $10 from 123456", rdelaney); em.merge(trans2);
		 */
		
		
		AccountEntity account = em.find(AccountEntity.class, "123456");
		System.out.println(account.getAccountNumber());
		System.out.println(account.getBalance());
		User user = account.getUser().convertToUser();
		System.out.println(user.getUserId());
		UserEntity userEntity = em.find(UserEntity.class, "rdelaney");
		System.out.println(userEntity.getId());
		List<AccountEntity> accounts = userEntity.getAccounts();
		for (int i = 0; i < accounts.size(); i++) {
			System.out.println(accounts.get(i).getAccountNumber());
		}
		
		Query query = em.createNamedQuery("Users.findAll");
		List<String> allUsers = query.getResultList();
		for (int i = 0; i < allUsers.size(); i ++) {
			System.out.println(allUsers.get(i));
		}
		System.out.println(allUsers.get(0).getClass().getName());
		System.out.println("drandall".getClass().getName());
		
		boolean isNewIdUnique = true;
		for (int i = 0; i < allUsers.size(); i ++) {
			if ("drandall".equals(allUsers.get(i))) {
				isNewIdUnique = false;
				break;
			}
		}
		
		System.out.println("drandall".equals(allUsers.get(0)));
		System.out.println(isNewIdUnique);
		
		Query query2 = em.createNamedQuery("Users.findTransactions");
		List<String> allTransactions = query.getResultList();
		for (int i = 0; i < allTransactions.size(); i ++) {
			System.out.println(allUsers.get(i));
		}
		
		/*
		 * AccountJPAImpl service = new AccountJPAImpl();
		 * service.updateTransactionHistory("rdelaney", "Withdrew 10 from 222333");
		 * List<TransactionEntity> trans = userEntity.getTransactions();
		 * System.out.println(trans.size());
		 * System.out.println(trans.get(0).getTransaction());
		 * System.out.println(trans.get(1).getTransaction());
		 */
		

		
		em.getTransaction().commit();
		em.close();
		
	}
		
	

}
