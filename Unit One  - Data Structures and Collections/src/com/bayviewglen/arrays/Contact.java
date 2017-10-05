package com.bayviewglen.arrays;

public class Contact implements Comparable{
	
	private String lname;
	private String fname;
	private String phone;
	
	public Contact(String fname, String lname, String phone) {
		super();
		this.lname = lname;
		this.fname = fname;
		this.phone = phone;
	}
	
	public Contact() {
		this.lname = "DEFAULT_LAST_NAME";
		this.fname = "DEFAULT_FIRST_NAME";
		this.phone = "DEFAULT_PHONE_NUMBER";
	}

	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int compareTo(Object o) {
		return lname.compareTo(((Contact)(o)).getLname());
	}
	
	@Override
	public String toString() {
		return this.lname;
	}
	
	public String forSaving() {
		return fname + lname + phone;
	}

	
	
}
