package com.smbcgroup.training.atm.dao.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.smbcgroup.training.atm.User;

@Entity

@Table(name = "Users")
@NamedNativeQueries({
    @NamedNativeQuery(name = "Users.findAll", query = "SELECT user_id FROM Users"),
    @NamedNativeQuery(name = "Users.findTransactions", query = "SELECT transactions FROM Users")
})
//@NamedNativeQuery(name="Users.findAll", query="SELECT user_id FROM Users")
//@NamedNativeQuery(name="Users.findTransactions", query="SELECT transactions FROM Users")
public class UserEntity {

	@Id
	@Column(name = "user_id")
	private String id;

	@OneToMany(mappedBy = "user")
	private List<AccountEntity> accounts;
	
	@OneToMany(mappedBy = "user")
	private List<TransactionEntity> transactions;

	public UserEntity() {

	}

	public UserEntity(String id) {
		this.id = id;
	}

	public User convertToUser() {
		User user = new User();
		user.setUserId(id);
		user.setAccounts(accounts.stream().map(AccountEntity::getAccountNumber).toArray(String[]::new));
		//user.setTransactions(transactions.stream().map(TransactionEntity::getTransaction).toArray(String[]::new));
		return user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountEntity> accounts) {
		this.accounts = accounts;
	}
	
	public List<TransactionEntity> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionEntity> transactions) {
		this.transactions = transactions;
	}
	

}
