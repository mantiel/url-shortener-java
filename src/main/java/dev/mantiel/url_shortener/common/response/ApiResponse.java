package dev.mantiel.url_shortener.common.response;

import org.springframework.http.HttpStatus;

public record ApiResponse(int code, String message) {

    public ApiResponse(final HttpStatus httpStatus, final String message) {
        this(httpStatus.value(), message);
    }

}
