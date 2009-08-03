package org.spring4gwt.examples.injection.server;

import org.spring4gwt.examples.injection.client.GreetingService;
import org.springframework.stereotype.Service;

/**
 * Server side greeting service implementation as a Spring bean named
 * greetingService.
 */
@Service("greetingService")
public class GreetingServiceImpl implements GreetingService {

	public String greet(String input) {
		return "Hello from the server, " + input + "!";
	}
}
