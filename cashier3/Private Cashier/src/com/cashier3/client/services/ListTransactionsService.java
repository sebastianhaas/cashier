package com.cashier3.client.services;

import java.util.List;

import com.cashier3.shared.Transaction;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("list_transactions")
public interface ListTransactionsService extends RemoteService {
	List<Transaction> retrieveTransactionList(RetrievingMode mode) throws IllegalArgumentException;
	
	public enum RetrievingMode {
		ALL
	} 
}
