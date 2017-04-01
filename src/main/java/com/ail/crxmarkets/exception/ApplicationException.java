package com.ail.crxmarkets.exception;

public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 3474106869577671312L;

	@SuppressWarnings("unused")
	public ApplicationException() {
	}

	@SuppressWarnings("unused")
	public ApplicationException(String message) {
		super(message);
	}

	@SuppressWarnings("unused")
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

	@SuppressWarnings("unused")
	public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
