package com.cashier3.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import com.cashier3.client.PrivateCashier;
import com.cashier3.client.services.ListTransactionsService;
import com.cashier3.shared.Transaction;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ListTransactionsServiceImpl extends RemoteServiceServlet implements
		ListTransactionsService {

	private PersistenceManager pm = PMF.get().getPersistenceManager();

	private static final Logger log = Logger.getLogger(PrivateCashier.class
			.getName());

	@Override
	public List<Transaction> retrieveTransactionList(RetrievingMode mode)
			throws IllegalArgumentException {

		ArrayList<Transaction> list = new ArrayList<Transaction>();

		try {
			Extent<DBTransaction> e = pm.getExtent(DBTransaction.class, false);
			for (DBTransaction dbta : e) {
				if (dbta != null) {
					Transaction ta = new Transaction();
					ta.setDate(dbta.getDate());
					ta.setValue(dbta.getValue());
					DBStore dbstore = pm.getObjectById(DBStore.class,
							dbta.getStore());
					DBUser dbuser = pm.getObjectById(DBUser.class,
							dbta.getUser());
					ta.setStore(dbstore.getName());
					ta.setUserMail(dbuser.getEmail());
					ta.setDisplayName(dbuser.getFirstName() + " "
							+ dbuser.getLastName());
					list.add(ta);
				} else {
					// TODO some error handling, if necessary. (catch?)
					log.severe("Datastore returned null references on DBTransaction objects.");
				}
			}
			e.closeAll();
		} catch (Exception e) {
			// TODO error handling
			log.warning(e.getMessage());
			log.warning("Error retrieving transactions");
		} finally {
			// pm.close();
		}
		return list;
	}
}