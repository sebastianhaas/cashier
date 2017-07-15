package com.cashier3.client.services;

import com.cashier3.client.services.AddUserService.InsertionResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AddUserService</code>.
 */
public interface AddUserServiceAsync {
	void addUser(String email, String password, String firstName, String lastName, AsyncCallback<InsertionResult> callback)
			throws IllegalArgumentException;
}
