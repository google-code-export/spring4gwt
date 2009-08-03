package org.spring4gwt.examples.simplerpc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point for the simple RPC example application.
 */
public class SimpleRPC implements EntryPoint {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while attempting to"
			+ "contact the server. Please check your network connection and try again.";

	/**
	 * Remote service proxy for the greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * Entry point method.
	 */
	public void onModuleLoad() {

		// Create name field
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		RootPanel.get("nameFieldContainer").add(nameField);
		nameField.setFocus(true);
		nameField.selectAll();

		// Create send button
		final Button sendButton = new Button("Send");
		RootPanel.get("sendButtonContainer").add(sendButton);

		// Create result field
		final HTML resultField = new HTML();
		RootPanel.get("resultFieldContainer").add(resultField);

		// Create a handler for the send button and name field
		class MyHandler implements ClickHandler, KeyUpHandler {

			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {

				// Disable send button
				sendButton.setEnabled(false);

				// Invoke greeting service
				greetingService.greet(nameField.getText(), new AsyncCallback<String>() {

					public void onFailure(Throwable caught) {
						// Show an error message
						sendButton.setEnabled(true);
						resultField.setHTML(SERVER_ERROR);
					}

					public void onSuccess(String result) {
						// Show the response
						sendButton.setEnabled(true);
						resultField.setHTML(result);
					}
				});
			}
		}

		// Add handler to the send button and name field
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}
}
