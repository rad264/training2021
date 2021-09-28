package com.smbcgroup.training.atm.dao.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.smbcgroup.training.atm.Account;
import com.smbcgroup.training.atm.User;

@Entity
@Table(name = "Transactions")
@NamedNativeQuery(name="Transactions.findAll", query="SELECT * FROM Transactions")
public class TransactionEntity {
	
	@Id @GeneratedValue
	@Column (name = "transaction_id")
	private Integer transactionId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	
	@Column
	private String transactionMessage;
	
	
	public TransactionEntity() {
		
	}
	
	public TransactionEntity(Integer transactionId, String transactionMessage, UserEntity user) {
		this.transactionId = transactionId;
		this.transactionMessage = transactionMessage;
		this.user = user;
	}
	
	public String convertToString() {
		String transaction = transactionMessage;
		return transaction;
		
	}
	

	public String getTransaction() {
		return transactionMessage;
	}

	public void setTransaction(String transactionMessage) {
		this.transactionMessage = transactionMessage;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public Integer getTransactionId() {
		return transactionId;
	}
	
	/*
	 * public void setTransactionId(Integer transactionId) { this.transactionId =
	 * transactionId; }
	 */
	

}
