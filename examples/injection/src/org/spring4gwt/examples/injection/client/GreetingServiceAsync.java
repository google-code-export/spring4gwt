package org.spring4gwt.examples.injection.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Client side async interface for the greeting service.
 */
public interface GreetingServiceAsync {

	void greet(String input, AsyncCallback<String> callback);
}
