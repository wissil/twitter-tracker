package org.interview.oauth.twitter.exceptions;

public class TwitterApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwitterApiException() {
		super();
	}
	
	public TwitterApiException(final String message) {
		super(message);
	}
	
	public TwitterApiException(final String message, final Throwable t) {
		super(message, t);
	}
	
	public TwitterApiException(final Throwable t) {
		super(t);
	}
}
