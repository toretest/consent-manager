package no.senseon.consentmanager.exception;

public final class AppResourceNotFoundException extends RuntimeException {

    public AppResourceNotFoundException() {
        super();
    }

    public AppResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppResourceNotFoundException(final String message) {
        super(message);
    }

    public AppResourceNotFoundException(final Throwable cause) {
        super(cause);
    }

}
