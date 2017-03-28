/*******************************************************************************
 *
 * Copyright (c) 2001-2017 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Jan 23, 2017 2:09:40 PM
 *******************************************************************************/

package org.gocom.devops.example;

/**
 * User. <br>
 *
 * @author ZhongWen Li (mailto: lizw@primeton.com)
 */
public class Employee {
	
	private String id;
	private String no;
	private String name;
	private String mail;
	private String password;
	private String phone;
	private String gender;
	
	public Employee() {
		super();
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the no.
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no The no to set.
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the mail.
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail The mail to set.
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return Returns the gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender The gender to set.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Employee [id=" + id + ", no=" + no + ", name=" + name + ", mail=" + mail + ", password=" + password
				+ ", phone=" + phone + ", gender=" + gender + "]";
	}
	
}
