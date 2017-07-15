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
public class DBStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean equals(Object o) {
		// check for self-comparison
		if (this == o)
			return true;
		if ( !(o instanceof DBStore) ) return false;
		 //cast to native object is now safe
	    DBStore that = (DBStore)o;
	    return that.getName().equalsIgnoreCase(this.getName());
	}
}
