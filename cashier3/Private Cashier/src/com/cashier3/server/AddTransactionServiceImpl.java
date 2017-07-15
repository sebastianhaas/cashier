package com.cashier3.server;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.cashier3.client.PrivateCashier;
import com.cashier3.client.services.AddTransactionService;
import com.cashier3.shared.Transaction;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AddTransactionServiceImpl extends RemoteServiceServlet implements
		AddTransactionService {

	private PersistenceManager pm = PMF.get().getPersistenceManager();

	private static final Logger log = Logger.getLogger(PrivateCashier.class
			.getName());

	@Override
	public InsertionResult addTransaction(Transaction t)
			throws IllegalArgumentException {

		DBTransaction ta = null;
		DBUser user = null;
		DBStore store = null;
		
		log.info("Preparing to add new transaction");
		
		try {
			// Retrieve stores
			Extent<DBStore> e = pm.getExtent(DBStore.class, false);
			for (DBStore s : e) {
				if (s.getName().equalsIgnoreCase(t.getStore())) {
					store = s;
					log.info("Found store in datastore");
					break;
				}
			}
		} catch (Exception e) {
			// TODO more error handling, show to user
			log.warning(e.getMessage());
			log.warning("Failed to retrieve stores list.");
			return InsertionResult.FAILED;
		}
		
		
		try {
			// Get user
			Query query = pm.newQuery(DBUser.class,
					"email == '"+t.getUserMail()+"'");
			@SuppressWarnings("unchecked")
			List<DBUser> results = (List<DBUser>) query.execute();
			if (results.size() == 1) {
				user = results.get(0);
				log.info("Found user's account");
			} else {
				// TODO error handling
				// this is a fatal error as the user is not found
				log.warning("Failed to find user's account");
				return InsertionResult.FAILED;
			}
		} catch (Exception e) {
			// TODO more error handling, show to user
			log.warning(e.getMessage());
			log.warning("Failed to retrieve user accounts");
			return InsertionResult.FAILED;
		}
		
		
		try {
			// if store does not exist create a new one
			if (store == null) {
				store = new DBStore();
				store.setName(t.getStore());
				pm.makePersistent(store);
				log.info("Store didn't exist, created new one");
			}
		} catch (Exception e) {
			// TODO more error handling, show to user
			log.warning(e.getMessage());
			log.warning("Failed to created new store");
			return InsertionResult.FAILED;
		}
		
		
		try {
			// compose new transaction
			ta = new DBTransaction();
			ta.setDate(t.getDate());
			ta.setStore(store.getKey());
			ta.setUser(user.getKey());
			ta.setValue(t.getValue());
			pm.makePersistent(ta);
		} catch (Exception e) {
			// TODO Error handling
			log.warning(e.getMessage());
			log.warning("Error writing to datastore");
			return InsertionResult.FAILED;
		}

		return InsertionResult.OK;
	}
}
