package com.cashier3.client.gui;

import com.cashier3.client.PrivateCashier;
import com.cashier3.shared.ConstantDefinitions;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import eu.maydu.gwt.validation.client.DefaultValidationProcessor;
import eu.maydu.gwt.validation.client.ValidationProcessor;
import eu.maydu.gwt.validation.client.actions.FocusAction;
import eu.maydu.gwt.validation.client.actions.StyleAction;
import eu.maydu.gwt.validation.client.validators.standard.RegularExpressionValidator;
import eu.maydu.gwt.validation.client.validators.strings.NameValidator;
import eu.maydu.gwt.validation.client.validators.strings.StringEqualsValidator;
import eu.maydu.gwt.validation.client.validators.strings.StringLengthValidator;

public class NewUserPopUp extends DialogBox implements
		ValueChangeHandler<String> {

	private ValidationProcessor validator;
	private Button ok;
	private Button cancel;
	private TextBox mailBox;
	private TextBox firstNameBox;
	private TextBox lastNameBox;
	private PasswordTextBox passwordBox;
	private PasswordTextBox passwordRepeatBox;

	public NewUserPopUp(final PrivateCashier parent) {
		ok = new Button("OK");
		// Disable ok button at startup so the user has to enter something
		// before confirming
		ok.setEnabled(false);
		this.cancel = new Button("Cancel");

		// Set the dialog box's caption.
		setText("Create new user");

		// Enable animation.
		setAnimationEnabled(true);

		// Enable glass background.
		setGlassEnabled(true);

		VerticalPanel panel = new VerticalPanel();

		// email unit
		validator = new DefaultValidationProcessor();
		HorizontalPanel emailPanel = new HorizontalPanel();
		emailPanel.add(new HTML("Email:"));
		mailBox = new TextBox();
		validator.addValidators("mail", new RegularExpressionValidator(mailBox,
				ConstantDefinitions.EMAIL_VALIDATION_REGEX, null)
				.addActionForFailure(new StyleAction("validationFailedBorder"))
				.addActionForFailure(new FocusAction()));
		mailBox.addValueChangeHandler(this);
		emailPanel.add(mailBox);
		panel.add(emailPanel);

		// firstName unit
		HorizontalPanel firstNamePanel = new HorizontalPanel();
		firstNamePanel.add(new HTML("First name:"));
		firstNameBox = new TextBox();
		validator.addValidators("firstName", new NameValidator(firstNameBox)
				.addActionForFailure(new StyleAction("validationFailedBorder"))
				.addActionForFailure(new FocusAction()));
		firstNameBox.addValueChangeHandler(this);
		firstNamePanel.add(firstNameBox);
		panel.add(firstNamePanel);

		// lastName unit
		HorizontalPanel lastNamePanel = new HorizontalPanel();
		lastNamePanel.add(new HTML("Last name:"));
		lastNameBox = new TextBox();
		validator.addValidators("lastName", new NameValidator(lastNameBox)
				.addActionForFailure(new StyleAction("validationFailedBorder"))
				.addActionForFailure(new FocusAction()));
		lastNameBox.addValueChangeHandler(this);
		lastNamePanel.add(lastNameBox);
		panel.add(lastNamePanel);

		// password unit
		HorizontalPanel passwordPanel = new HorizontalPanel();
		passwordPanel.add(new HTML("Password:"));
		passwordBox = new PasswordTextBox();
		validator.addValidators(
				"password",
				new StringLengthValidator(passwordBox,
						ConstantDefinitions.MIN_PASSWORD_LENGTH,
						ConstantDefinitions.MAX_PASSWORD_LENGTH)
						.addActionForFailure(
								new StyleAction("validationFailedBorder"))
						.addActionForFailure(new FocusAction()));
		passwordBox.addValueChangeHandler(this);
		passwordPanel.add(passwordBox);
		panel.add(passwordPanel);

		// password repeat unit
		HorizontalPanel passwordRepeatPanel = new HorizontalPanel();
		passwordRepeatPanel.add(new HTML("Repeat password:"));
		passwordRepeatBox = new PasswordTextBox();
		validator.addValidators(
				"passwordsEqual",
				new StringEqualsValidator(passwordRepeatBox, passwordBox)
						.addActionForFailure(
								new StyleAction("validationFailedBorder"))
						.addActionForFailure(new FocusAction()));
		passwordRepeatBox.addValueChangeHandler(this);
		passwordRepeatPanel.add(passwordRepeatBox);
		panel.add(passwordRepeatPanel);

		// Create buttons
		HorizontalPanel buttonPanel = new HorizontalPanel();
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parent.processNewUserRequest(mailBox.getValue().trim(),
						firstNameBox.getValue().trim(), lastNameBox.getValue()
								.trim(), passwordBox.getValue().trim());
				NewUserPopUp.this.hide();
			}
		});
		buttonPanel.add(ok);
		cancel.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				NewUserPopUp.this.hide();
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
		ok.setEnabled(validator.validate("mail")
				&& validator.validate("firstName")
				&& validator.validate("lastName")
				&& validator.validate("password")
				&& validator.validate("passwordsEqual"));
	}
}
