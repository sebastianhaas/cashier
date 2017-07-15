/**
 * 
 */
package com.cashier3.server;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * The basic data template for a transaction
 * 
 * @author Sebastian Haas
 * 
 */
@PersistenceCapable
public class DBTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This transaction's unique key
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	/**
	 * The value of this transaction
	 */
	@Persistent
	private double value;

	/**
	 * The date this transaction was made
	 */
	@Persistent
	private Date date;

	/**
	 * The store in which this transaction was made
	 */
	@Persistent
	private Key store;

	/**
	 * The user who has done this transaction
	 */
	@Persistent
	private Key user;

	/**
	 * Returns this transaction's key
	 * 
	 * @return The key of this transaction
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param store
	 *            the store to set
	 */
	public void setStore(Key store) {
		this.store = store;
	}

	/**
	 * @return the store
	 */
	public Key getStore() {
		return store;
	}

	/**
	 * @param date
	 *            the date to set
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
	 * @param value
	 *            the value to set
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
	 * @param user
	 *            the user to set
	 */
	public void setUser(Key user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public Key getUser() {
		return user;
	}
}
