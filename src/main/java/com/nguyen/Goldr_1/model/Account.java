package com.nguyen.Goldr_1.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
@JsonSerialize
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@JsonBackReference
	@ManyToOne(targetEntity = User.class)
	private User user;

//	mappedBy creates the join column in the target table
//	cascade ALL deletes all child amounts if the parent account is deleted
//	fetch type is defaulted to LAZY, and child Txns are not loaded along with the parent Account
	@OneToMany(mappedBy = "account", targetEntity = Txn.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Txn> txns;

	public Account() {
		this.name = "";
		this.user = null;
		this.txns = null;
	}

	public Account(int id, String name, User user, List<Txn> txns) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.txns = txns;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Txn> getTxns() {
		return txns;
	}

	public void setTxns(List<Txn> txns) {
		this.txns = txns;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", user=" + user + ", txns=" + txns + "]";
	}

}