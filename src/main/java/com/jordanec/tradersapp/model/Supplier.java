package com.jordanec.tradersapp.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author jordan
 *
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope=Supplier.class)
@Table(name="Supplier", catalog="tradersappdb_v1")
public class Supplier implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	String company;
	String address;
	Integer phone;
	
	@ManyToMany(mappedBy="suppliers",fetch=FetchType.EAGER)
	//@JsonBackReference
	@JsonIgnore
	Collection<Product> products;
	
	public Supplier() {	}
	
	public Supplier(Long id, String name, String company, String address, Integer phone) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.address = address;
		this.phone = phone;
	}

	public Supplier(Long id, String name, String company, String address, Integer phone, Collection<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.address = address;
		this.phone = phone;
		this.products = products;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	@JoinTable(name = "Supplier_Products", catalog = "tradersappdb_v1", joinColumns = {
			@JoinColumn(name = "id", nullable = true, updatable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "id", nullable = true, updatable = true) })
	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\",\"name\":\"" + name + "\"}";
	}
	
	
}
