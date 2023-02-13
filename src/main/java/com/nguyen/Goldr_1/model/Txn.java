package com.nguyen.Goldr_1.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Txn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private double amount;
	private LocalDate date;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Account.class)
	private Account account;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Asset.class)
	private Asset asset;
	
	@JsonBackReference
	@ManyToOne(targetEntity = User.class)
	private User user;

	public Txn() {
		this.amount = 0.0;
		this.date = null;
		this.account = null;
		this.asset = null;
		this.user = null;
	}

	public Txn(int id, double amount, LocalDate date, Account account, Asset asset, User user) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.account = account;
		this.asset = asset;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Txn [id=" + id + ", amount=" + amount + ", date=" + date + ", account=" + account + ", asset=" + asset
				+ ", user=" + user + "]";
	}

}
