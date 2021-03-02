package com.adidas.exception;

/**
 * Custom exception
 * @author SILVIA
 *
 */
public class SubscriptionException extends Exception {

	private static final long serialVersionUID = 2128494936381328005L;

	public SubscriptionException(String message) {
		super(message);
	}
	
	public SubscriptionException(String message, Throwable t) {
		super(message, t);
	}
}
