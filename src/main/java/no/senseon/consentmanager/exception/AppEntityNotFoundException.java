package no.senseon.consentmanager.exception;

public final class AppEntityNotFoundException extends RuntimeException {

    public AppEntityNotFoundException() {
        super();
    }

    public AppEntityNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppEntityNotFoundException(final String message) {
        super(message);
    }

    public AppEntityNotFoundException(final Throwable cause) {
        super(cause);
    }

}
