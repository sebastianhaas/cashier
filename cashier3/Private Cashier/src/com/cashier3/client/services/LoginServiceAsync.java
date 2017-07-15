package com.cashier3.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
	void login(String email, String password, AsyncCallback<Void> callback)
			throws IllegalArgumentException;
}
