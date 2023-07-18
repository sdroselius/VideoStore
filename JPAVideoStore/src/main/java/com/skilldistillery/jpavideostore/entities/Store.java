package com.skilldistillery.jpavideostore.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToOne
	@JoinColumn(name = "manager_id")
	private Staff manager;

	@OneToMany(mappedBy = "store")
	private List<Customer> customers;

	@OneToMany(mappedBy = "store")
	private List<Staff> employees;

	public Store() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Staff getManager() {
		return manager;
	}

	public void setManager(Staff manager) {
		this.manager = manager;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void addCustomer(Customer customer) {
		if (customers == null) {
			customers = new ArrayList<>();
		}
		if (!customers.contains(customer)) {
			customers.add(customer);
			customer.setStore(this);
		}
	}

	public void removeCustomer(Customer customer) {
		if (customers != null && customers.contains(customer)) {
			customers.remove(customer);
			customer.setStore(null);
		}
	}

	public List<Staff> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Staff> employees) {
		this.employees = employees;
	}

	public void addEmployee(Staff employee) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		if (!employees.contains(employee)) {
			employees.add(employee);
			employee.setStore(this);
		}
	}

	public void removeEmployee(Staff employee) {
		if (employees != null && employees.contains(employee)) {
			employees.remove(employee);
			employee.setStore(null);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Store [id=").append(id).append(", address=").append(address).append("]");
		return builder.toString();
	}

}