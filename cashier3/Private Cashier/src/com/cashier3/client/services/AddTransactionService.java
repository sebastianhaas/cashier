package com.cashier3.client.services;

import com.cashier3.shared.Transaction;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("add_transaction")
public interface AddTransactionService extends RemoteService {
	InsertionResult addTransaction(Transaction t) throws IllegalArgumentException;
	
	public enum InsertionResult {
		//TODO more subtile error reporting (more states...)
		OK, FAILED
	} 
}
