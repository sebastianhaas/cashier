package com.cashier3.client.gui;

import java.util.Date;

import com.cashier3.client.PrivateCashier;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import eu.maydu.gwt.validation.client.DefaultValidationProcessor;
import eu.maydu.gwt.validation.client.ValidationProcessor;
import eu.maydu.gwt.validation.client.actions.FocusAction;
import eu.maydu.gwt.validation.client.actions.StyleAction;
import eu.maydu.gwt.validation.client.validators.numeric.DoubleValidator;
import eu.maydu.gwt.validation.client.validators.standard.NotEmptyValidator;

public class NewTransactionPopUp extends DialogBox implements
		ValueChangeHandler<String> {

	private PrivateCashier parent;
	private ValidationProcessor validator;
	private Button ok;
	private Button cancel;
	private SuggestBox shopBox;
	private TextBox priceBox;
	private DateBox dateBox;

	public NewTransactionPopUp(PrivateCashier parent) {
		this.parent = parent;
		ok = new Button("OK");
		// Disable ok button at startup so the user has to enter something
		// before confirming
		ok.setEnabled(false);
		this.cancel = new Button("Cancel");

		// Set the dialog box's caption.
		setText("Create new transaction");

		// Enable animation.
		setAnimationEnabled(true);

		// Enable glass background.
		setGlassEnabled(true);

		VerticalPanel panel = new VerticalPanel();

		// value unit
		validator = new DefaultValidationProcessor();
		HorizontalPanel pricePanel = new HorizontalPanel();
		pricePanel.add(new HTML("Value:"));
		priceBox = new TextBox();
		validator.addValidators(
				"value",
				new DoubleValidator(priceBox, 0f, Float.MAX_VALUE)
						.addActionForFailure(
								new StyleAction("validationFailedBorder"))
						.addActionForFailure(new FocusAction()));
		priceBox.addValueChangeHandler(this);
		pricePanel.add(priceBox);
		pricePanel.add(new HTML("€"));
		panel.add(pricePanel);

		// shop unit
		HorizontalPanel shopPanel = new HorizontalPanel();
		shopPanel.add(new HTML("Shop:"));
		// Define the oracle that finds suggestions
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		String[] shops = parent.getCurrentShopList();
		for (int i = 0; i < shops.length; ++i) {
			oracle.add(shops[i]);
		}
		shopBox = new SuggestBox(oracle);
		shopBox.setLimit(10);
		validator.addValidators("shop", new NotEmptyValidator(shopBox, true)
				.addActionForFailure(new StyleAction("validationFailedBorder"))
				.addActionForFailure(new FocusAction()));
		shopBox.addValueChangeHandler(this);
		shopPanel.add(shopBox);
		panel.add(shopPanel);

		// Create a DateBox
		HorizontalPanel datePanel = new HorizontalPanel();
		datePanel.add(new HTML("Date:"));
		DateTimeFormat dateFormat = DateTimeFormat
				.getFormat(PredefinedFormat.DATE_TIME_MEDIUM);
		dateBox = new DateBox();
		dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				ok.setEnabled(validator.validate("value")
						&& validator.validate("shop")
						&& dateBox.getValue() != null);
			}
		});
		datePanel.add(dateBox);
		panel.add(datePanel);

		// Create buttons
		HorizontalPanel buttonPanel = new HorizontalPanel();
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				NewTransactionPopUp.this.parent.processNewTransactionRequest(
						Double.parseDouble(NewTransactionPopUp.this.priceBox
								.getValue()), NewTransactionPopUp.this.shopBox
								.getValue(), NewTransactionPopUp.this.dateBox
								.getValue());
				NewTransactionPopUp.this.hide();
			}
		});
		buttonPanel.add(ok);
		cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				NewTransactionPopUp.this.hide();
			}
		});
		buttonPanel.add(cancel);
		panel.add(buttonPanel);
		setWidget(panel);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		// Will be called every time the user enters some data to enable/disable
		// ok button
		ok.setEnabled(validator.validate("value") && validator.validate("shop")
				&& dateBox.getValue() != null);
	}
}
