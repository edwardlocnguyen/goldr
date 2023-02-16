package com.nguyen.Goldr_1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Nonnull;
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
	@Nonnull
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String occupation;
	private LocalDate dob;
	private Integer creditScore;

	@JsonManagedReference
	@OneToMany(mappedBy = "user", targetEntity = Asset.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Asset> assets;

//	mappedBy creates the join column in the target table
//	cascade ALL deletes all child accounts if the parent user is deleted
//	fetch type is defaulted to LAZY, and child Accounts are not loaded along with the parent User
//		update: needed to change to LAZY to delete accounts
	@JsonManagedReference
	@OneToMany(mappedBy = "user", targetEntity = Account.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts;

//	mappedBy creates the join column in the target table
//	cascade ALL deletes all child accounts if the parent user is deleted
	@JsonManagedReference
	@OneToMany(mappedBy = "user", targetEntity = Txn.class, cascade = CascadeType.ALL)
	private List<Txn> txns;

	public User() {
		this.email = "";
		this.password = "";
		this.firstName = "";
		this.lastName = "";
		this.occupation = "";
		this.dob = null;
		this.creditScore = 0;
		this.assets = null;
		this.accounts = null;
		this.txns = null;
	}

	public User(int id, String email, String password, String firstName, String lastName, String occupation,
			LocalDate dob, Integer creditScore, List<Asset> assets, List<Account> accounts, List<Txn> txns) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.occupation = occupation;
		this.dob = dob;
		this.creditScore = creditScore;
		this.assets = assets;
		this.accounts = accounts;
		this.txns = txns;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(Integer creditScore) {
		this.creditScore = creditScore;
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
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
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", occupation=" + occupation + ", dob=" + dob + ", creditScore="
				+ creditScore + ", assets=" + assets + ", accounts=" + accounts + ", txns=" + txns + "]";
	}

}
