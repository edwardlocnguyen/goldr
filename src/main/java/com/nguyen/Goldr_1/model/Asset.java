package com.nguyen.Goldr_1.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Asset implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

//	mappedBy creates the join column in the target table
//	cascade ALL deletes all child txns if the parent asset is deleted
//	fetch type is defaulted to LAZY, and child Txns are not loaded along with the parent Asset
	@JsonManagedReference
	@OneToMany(mappedBy = "asset", targetEntity = Txn.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Txn> txns;

	public Asset() {
		this.name = "";
		this.txns = null;
	}

	public Asset(int id, String name, List<Txn> txns) {
		super();
		this.id = id;
		this.name = name;
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
		return "Asset [id=" + id + ", name=" + name + ", txns=" + txns + "]";
	}

}