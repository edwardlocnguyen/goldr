package com.nguyen.Goldr_1.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true)
	private String username;
	private String password;
	private String email;
	private Integer age;

//	mappedBy creates the join column in the target table
//	cascade ALL deletes all child accounts if the parent user is deleted
//	fetch type is defaulted to LAZY, and child Accounts are not loaded along with the parent User
	@OneToMany(mappedBy = "user", targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Account> accounts;
//	mappedBy creates the join column in the target table
//	cascade ALL deletes all child accounts if the parent user is deleted
	@OneToMany(mappedBy = "user", targetEntity = Txn.class, cascade = CascadeType.ALL)
	private List<Txn> txns;

	public User() {
		this.username = "";
		this.password = "";
		this.email = "";
		this.age = 0;
		this.accounts = null;
		this.txns = null;
	}

	public User(int id, String username, String password, String email, Integer age, List<Account> accounts,
			List<Txn> txns) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.age = age;
		this.accounts = accounts;
		this.txns = txns;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", age="
				+ age + ", accounts=" + accounts + ", txns=" + txns + "]";
	}

}
