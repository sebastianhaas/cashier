package com.cashier3.client;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.cashier3.client.gui.EastPanel;
import com.cashier3.client.gui.LoginScreen;
import com.cashier3.client.gui.TransactionTable;
import com.cashier3.client.services.AddTransactionService;
import com.cashier3.client.services.AddTransactionServiceAsync;
import com.cashier3.client.services.AddUserService;
import com.cashier3.client.services.AddUserServiceAsync;
import com.cashier3.client.services.ListTransactionsService;
import com.cashier3.client.services.ListTransactionsServiceAsync;
import com.cashier3.client.services.AddTransactionService.InsertionResult;
import com.cashier3.shared.Transaction;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class PrivateCashier implements EntryPoint {
	/**
	 * Create remote service proxies to talk to the server-side transaction
	 * services
	 */
	private final ListTransactionsServiceAsync listTransactionsService = GWT
			.create(ListTransactionsService.class);
	private final AddTransactionServiceAsync addTransactionsService = GWT
			.create(AddTransactionService.class);
	private final AddUserServiceAsync addUserService = GWT
	.create(AddUserService.class);

	/**
	 * Get logger
	 */
	private static final Logger log = Logger.getLogger(PrivateCashier.class
			.getName());

	/**
	 * Use this field to determine wheter a user is logged in or not. (if null)
	 */
	private String loggedInUser = null;

	/**
	 * Some UI elements
	 */
	private DockLayoutPanel mainDockPanel;
	private FlowPanel tablePanel;
	private EastPanel eastPanel;
	private TransactionTable table;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		if (getLoggedInUser() == null) {
			RootPanel.get("loginContent").add(new LoginScreen(this));
		} else {
			mainDockPanel = new DockLayoutPanel(Unit.EM);
			
			tablePanel = new FlowPanel();
			table = new TransactionTable();
			fillTransactionsTable();
			tablePanel.add(table.getTable());
			
			mainDockPanel
					.addNorth(

							new HTML(
									"<h1><img src=\"images/logo.png\" /> Private Cashier Pre-Alpha </h1>"),
							2);
			mainDockPanel.addSouth(new HTML("south"), 2);
			mainDockPanel.addEast(eastPanel = new EastPanel(this), 100);
			mainDockPanel.add(tablePanel);

			RootLayoutPanel rp = RootLayoutPanel.get();
			rp.add(mainDockPanel);
		}
	}
	
	/**
	 * Sets the email address (ID) of the currently logged in user. This setter
	 * will ONLY have effect in case loggedInUser is null
	 * 
	 * @param loggedInUser
	 *            the loggedInUser to set
	 */
	public void setLoggedInUser(String loggedInUser) {
		if (this.loggedInUser == null) {
			this.loggedInUser = loggedInUser;
		}
	}

	/**
	 * Returns the mail address of the currently logged in user
	 * @return the logged in user's mail address (ID)
	 */
	public String getLoggedInUser() {
		return loggedInUser;
	}
	
	public void fillTransactionsTable() {
		listTransactionsService.retrieveTransactionList(
				ListTransactionsService.RetrievingMode.ALL,
				new AsyncCallback<List<Transaction>>() {
					public void onFailure(Throwable caught) {
						// TODO Error handling
						log.warning("Error during server request. [listTAServlet]");
					}

					@Override
					public void onSuccess(List<Transaction> result) {
						for (Transaction ta : result) {
							table.addTransaction(ta);
						}
						table.updateTable();
					}
				});
	}

	public String[] getCurrentShopList() {
		// TODO implement working function
		String[] ret = { "Billa", "Hofer", "Merkur", "Saturn", "Wiener Linien" };
		return ret;
	}

	public void processNewTransactionRequest(double value, String shop,
			Date datetime) {
		// TODO more error handling, double checking, user feeedback etc.
		Transaction ta = new Transaction();
		ta.setDate(datetime);
		ta.setStore(shop);
		ta.setValue(value);
		ta.setUserMail(loggedInUser);

		addTransactionsService.addTransaction(ta,
				new AsyncCallback<InsertionResult>() {
					public void onFailure(Throwable caught) {
						// TODO Error handling
						log.warning("Error during server request. [addTAServlet]");
					}

					@Override
					public void onSuccess(InsertionResult result) {
						if (result == InsertionResult.OK) {
							// TODO give user feedback
							log.info("Added transaction");
							// Process update in transaction table
							table.emptyTableData();
							fillTransactionsTable();
							table.updateTable();
						} else {
							// TODO error handling
							log.warning("Adding transaction failed");
						}
					}
				});
	}
	
	public void processNewUserRequest(String mail, String firstName, String lastName, String password){
		// TODO implement input fields
		addUserService.addUser(mail, password, firstName, lastName, new AsyncCallback<AddUserService.InsertionResult>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO more error feedback
				log.warning("Failed to create new user");
			}

			@Override
			public void onSuccess(AddUserService.InsertionResult result) {
				// TODO more success feedback
				if(result == AddUserService.InsertionResult.OK) {
					log.fine("Created new user");
				} else {
					// TODO react to other enum states than ok (higher granularity)
					log.warning("Failed to create new user.");
				}
			}
			
		});
	}
}