package dev.mantiel.url_shortener;

import dev.mantiel.url_shortener.common.exception.InvalidFormatURLException;
import dev.mantiel.url_shortener.common.exception.NotFoundException;
import dev.mantiel.url_shortener.common.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(final NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiErrorResponse(
                        HttpStatus.NOT_FOUND,
                        "NOT_FOUND",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(InvalidFormatURLException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidFormatURL(final InvalidFormatURLException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        "INVALID_FORMAT_URL",
                        exception.getMessage()
                ));
    }

}
