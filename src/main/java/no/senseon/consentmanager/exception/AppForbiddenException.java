package no.senseon.consentmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when user is forbidden to execute specified operation or access specified data.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AppForbiddenException extends RuntimeException {

    public AppForbiddenException() {
        super();
    }

    public AppForbiddenException(final String message) {
        super(message);
    }

    public AppForbiddenException(final Throwable cause) {
        super(cause);
    }

}
