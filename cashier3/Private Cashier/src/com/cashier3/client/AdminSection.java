/**
 * 
 */
package com.cashier3.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * @author Sebastian Haas
 *
 */
public class AdminSection implements EntryPoint {

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	@Override
	public void onModuleLoad() {
		RootLayoutPanel rp = RootLayoutPanel.get();
		rp.add(new HTML("TEST!!"));
	}
}
