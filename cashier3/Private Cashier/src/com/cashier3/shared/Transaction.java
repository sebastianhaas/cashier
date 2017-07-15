package com.cashier3.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sebastian Haas
 * 
 */
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private double value;
	private Date date;
	private String store;
	private String userMail;
	private String displayName;

	/**
	 * @param store the store to set
	 */
	public void setStore(String store) {
		this.store = store;
	}

	/**
	 * @return the store
	 */
	public String getStore() {
		return store;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param userMail the userMail to set
	 */
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	/**
	 * @return the userMail
	 */
	public String getUserMail() {
		return userMail;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
}
