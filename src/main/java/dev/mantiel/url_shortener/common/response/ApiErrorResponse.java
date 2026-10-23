package dev.mantiel.url_shortener.common.response;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ApiErrorResponse(
        int code,
        String error,
        String message,
        Instant timestamp
) {

    public ApiErrorResponse(final HttpStatus httpStatus, final String error, final String message, final Instant timestamp) {
        this(httpStatus.value(), error, message, timestamp);
    }

    public ApiErrorResponse(final HttpStatus httpStatus, final String error, final String message) {
        this(httpStatus, error, message, Instant.now());
    }

}
