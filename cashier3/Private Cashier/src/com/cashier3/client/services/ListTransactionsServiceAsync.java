package com.cashier3.client.services;

import java.util.List;

import com.cashier3.shared.Transaction;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ListTransactionsService</code>.
 */
public interface ListTransactionsServiceAsync {
	void retrieveTransactionList(ListTransactionsService.RetrievingMode mode, AsyncCallback<List<Transaction>> callback)
			throws IllegalArgumentException;
}
