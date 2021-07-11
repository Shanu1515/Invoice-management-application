package com.higradius;

import java.sql.Date;

public class Response {
	private String name_customer;
	private String cust_number;
	//private String name_customer;
	private long invoice_id;
	private Integer total_open_amount;
 
	private Date due_in_date;
	private Date predicted_payment_date;
	//private String notes;

	public String getName_customer() {
		return name_customer;
	}

	public void setName_customer(String name_customer) {
		this.name_customer = name_customer;
	}
public String getCust_number() {
		return cust_number;
	}

	public void setCust_number(String cust_number) {
		this.cust_number = cust_number;
	}

	

	public long getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(long invoice_id) {
		this.invoice_id = invoice_id;
	}

	public Integer getTotal_open_amount() {
		return total_open_amount;
	}

	public void setTotal_open_amount(Integer total_open_amount) {
		this.total_open_amount = total_open_amount;
	}

	public Date getDue_in_date() {
		return due_in_date;
	}

	public void setDue_in_date(Date due_in_date) {
		this.due_in_date = due_in_date;
	}

	public Date getPredicted_payment_date() {
		return predicted_payment_date;
	}

	public void setPredicted_payment_date(Date predicted_payment_date) {
		this.predicted_payment_date = predicted_payment_date;
	}

	/*public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}*/


}
