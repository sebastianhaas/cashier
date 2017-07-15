package com.cashier3.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("add_user")
public interface AddUserService extends RemoteService {
	InsertionResult addUser(String email, String password, String firstName, String lastName) throws IllegalArgumentException;
	
	public enum InsertionResult {
		//TODO more subtile error reporting (more states...)
		OK, ALREADY_EXISTS_ERROR, FAILED
	} 
}
