/*******************************************************************************
 *
 * Copyright (c) 2001-2016 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Feb 23, 2017 5:11:58 PM
 *******************************************************************************/

package org.gocom.devops.example;

/**
 * JdbcConfiguration.
 *
 * @author ZhongWen Li (mailto:lizw@primeton.com)
 */
public class JdbcConfiguration {
	
	private String url;
	private String user;
	private String password;
	
	/**
	 * 
	 */
	public JdbcConfiguration() {
		super();
	}

	/**
	 * @param url
	 * @param user
	 * @param password
	 */
	public JdbcConfiguration(String url, String user, String password) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the user.
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user The user to set.
	 */
	public void setUser(String user) {
		this.user = user;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JdbcConfiguration [url=" + url + ", user=" + user + ", password=" + password + "]";
	}
	
}
