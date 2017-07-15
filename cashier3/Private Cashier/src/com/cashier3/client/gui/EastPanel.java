package com.cashier3.client.gui;

import com.cashier3.client.PrivateCashier;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EastPanel extends HorizontalPanel {
	
	/**
	 * Some UI elements
	 */
	private VerticalPanel innerPanel;
	private Button newEntryButton;
	private Button adminButton;
	private final PrivateCashier parent;
	
	public EastPanel(PrivateCashier parent) {
		this.parent = parent;
		
		setWidth("100%");
		setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
				
		innerPanel = new VerticalPanel();
		innerPanel.setSpacing(5);
		innerPanel.setWidth("200px");
		innerPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		
		//Display hints panel
		DisclosurePanel hintsPanel = new DisclosurePanel("Did you know?");
		hintsPanel.setAnimationEnabled(true);
		hintsPanel
				.setContent(new HTML(
						"You can add a new row of data to this form by pressing the button below"));
	    DecoratorPanel decPanelHints = new DecoratorPanel();
	    decPanelHints.setWidget(hintsPanel);
		innerPanel.add(decPanelHints);
		
		//Display add new entry panel
		newEntryButton = new Button("Create");
		newEntryButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	NewTransactionPopUp popUp = new NewTransactionPopUp(EastPanel.this.parent);
	        	popUp.center();
	        	popUp.show();
	          }
	        });
		Grid grid = new Grid(2,1);
		grid.setCellSpacing(5);
		grid.setHTML(0, 0, "Click to add a new entry");
		grid.setWidget(1, 0, newEntryButton);
	    DecoratorPanel decPanelNewEntry = new DecoratorPanel();
	    decPanelNewEntry.setWidget(grid);
		innerPanel.add(decPanelNewEntry);
		
		
		// Admin section
		Grid admingrid = new Grid(2,1);
		admingrid.setCellSpacing(5);
		admingrid.setHTML(0, 0, "Admin section");
		adminButton = new Button("Create user");
		adminButton.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	NewUserPopUp popUp = new NewUserPopUp(EastPanel.this.parent);
	        	popUp.center();
	        	popUp.show();
	          }
	        });
		admingrid.setWidget(1, 0, adminButton);
	    DecoratorPanel decPanelAdmin = new DecoratorPanel();
	    decPanelAdmin.setWidget(admingrid);
		innerPanel.add(decPanelAdmin);
		
		add(innerPanel);
	}
}