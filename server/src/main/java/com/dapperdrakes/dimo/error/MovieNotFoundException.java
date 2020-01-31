package com.dapperdrakes.dimo.error;

public class MovieNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 5861310537366234163L;

    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundException(final String message) {
        super(message);
    }

    public MovieNotFoundException(final Throwable cause) {
        super(cause);
    }

}
