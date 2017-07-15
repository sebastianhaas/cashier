/**
 * 
 */
package com.cashier3.server;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author Sebastian Haas
 *
 */
@PersistenceCapable
public class DBUser implements Serializable {
	
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
	 * The name of this user
	 */
	@Persistent
	private String firstName;
	@Persistent
	private String lastName;
	
	/**
	 * The user's email address, designed to be unique
	 */
	@Persistent
	private String email;
	
	/**
	 * The user's password
	 */
	@Persistent
	private byte[] password;

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public String toString() {
		return getFirstName() + " " + getLastName();
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public byte[] getPassword() {
		return password;
	}
}
