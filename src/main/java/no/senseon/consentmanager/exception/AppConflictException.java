package no.senseon.consentmanager.exception;

public final class AppConflictException extends RuntimeException {

    public AppConflictException() {
        super();
    }

    public AppConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppConflictException(final String message) {
        super(message);
    }

    public AppConflictException(final Throwable cause) {
        super(cause);
    }

}
