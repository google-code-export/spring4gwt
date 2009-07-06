package org.spring4gwt.examples.simplerpc.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Client side interface for the greeting service.
 */
@RemoteServiceRelativePath("springGwtServices/greetingService")
public interface GreetingService extends RemoteService {

	String greet(String name);
}
