package com.cashier3.client.services;

import com.cashier3.client.services.AddTransactionService.InsertionResult;
import com.cashier3.shared.Transaction;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AddTransactionService</code>.
 */
public interface AddTransactionServiceAsync {
	void addTransaction(Transaction t, AsyncCallback<InsertionResult> callback)
			throws IllegalArgumentException;
}
