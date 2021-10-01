package com.smbcgroup.training.atm.dao.jpa;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.smbcgroup.training.atm.Transaction;

@Entity
@Table(name = "Transactions")
public class TransactionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private long id;

	@Temporal(value=TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "description")
	private String description;
		
	@ManyToOne
	@JoinColumn(name = "account_number")
	private AccountEntity account;

	public TransactionEntity() {

	}

	public TransactionEntity(AccountEntity account, BigDecimal amount, String type, String description) {
		this.account = account;
		this.amount = amount;
		this.type = type;
		this.description = description;
	}
	
	public Transaction convertToTransaction() {
		Transaction transaction = new Transaction();
		transaction.setTransactionId(id);
		transaction.setAmount(amount);
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		String date = df.format(createdTime);
		transaction.setCreatedTime(date);
		transaction.setType(type);
		transaction.setDescription(description);
		transaction.setAccountNumber(account.getAccountNumber());
		return transaction;
	}

	public long getTransactionId() {
		return id;
	}

	public void setTransactionId(long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}
	
	public Date getCreatedTime() {
		return createdTime;
	}
	
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
