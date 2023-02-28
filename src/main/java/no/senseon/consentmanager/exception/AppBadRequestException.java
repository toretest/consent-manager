package no.senseon.consentmanager.exception;

public final class AppBadRequestException extends RuntimeException {

    public AppBadRequestException() {
        super();
    }

    public AppBadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppBadRequestException(final String message) {
        super(message);
    }

    public AppBadRequestException(final Throwable cause) {
        super(cause);
    }

}
