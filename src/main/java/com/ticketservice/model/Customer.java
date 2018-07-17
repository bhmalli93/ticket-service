package com.ticketservice.model;

/* for simplicity just creating customer with id and email, skipping other customer details
    like address, username, password, card info etc*/
public class Customer {

	private int customerId;
	private String customerEmail;
	private String customerName;

	public Customer(int customerId, String customerEmail) {
		this.customerId = customerId;
		this.customerEmail = customerEmail;
	}

	public Customer(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Customer{" + "customerId=" + customerId + ", customerEmail='" + customerEmail + '\''
				+ ", customerName='" + customerName + '\'' + '}';
	}
}
