package dev.mantiel.url_shortener.common.exception;

public final class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }

}
