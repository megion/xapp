package org.megion.xapp.core.aop.interceptor;

public class NoTransactionException extends RuntimeException {

	private static final long serialVersionUID = -2107777744535278421L;

	public NoTransactionException() {
		super();
	}

	public NoTransactionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoTransactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoTransactionException(String message) {
		super(message);
	}

	public NoTransactionException(Throwable cause) {
		super(cause);
	}

}
