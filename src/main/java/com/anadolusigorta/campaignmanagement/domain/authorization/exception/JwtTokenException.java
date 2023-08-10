/* dks20165 created on 14.07.2021 inside the package - com.anadolusigorta.campaignmanagement.domain.authorization.exception */

package com.anadolusigorta.campaignmanagement.domain.authorization.exception;
public class JwtTokenException extends RuntimeException {

	public JwtTokenException(String message) {
		super(message);
	}

	public JwtTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public JwtTokenException(Throwable cause) {
		super(cause);
	}

	public JwtTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}