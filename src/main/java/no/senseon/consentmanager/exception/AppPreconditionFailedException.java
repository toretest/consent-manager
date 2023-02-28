package no.senseon.consentmanager.exception;

public final class AppPreconditionFailedException extends RuntimeException {

    public AppPreconditionFailedException() {
        super();
    }

    public AppPreconditionFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public AppPreconditionFailedException(final String message) {
        super(message);
    }

    public AppPreconditionFailedException(final Throwable cause) {
        super(cause);
    }

}
