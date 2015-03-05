package org.megion.xapp.core.aop.interceptor;

public class YesTransactionException extends RuntimeException {

	private static final long serialVersionUID = 4236253391366002598L;

	public YesTransactionException() {
		super();
	}

	public YesTransactionException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public YesTransactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public YesTransactionException(String message) {
		super(message);
	}

	public YesTransactionException(Throwable cause) {
		super(cause);
	}

}
