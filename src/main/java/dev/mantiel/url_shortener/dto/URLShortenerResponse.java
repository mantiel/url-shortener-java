package dev.mantiel.url_shortener.dto;

import dev.mantiel.url_shortener.repository.URLShortener;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

public record URLShortenerResponse(
        @NotBlank
        String code,
        @NotBlank
        String originalUrl,
        Instant createdAt
) {

    public static URLShortenerResponse from(final URLShortener urlShortener) {
        return new URLShortenerResponse(urlShortener.code(), urlShortener.originalUrl(), urlShortener.createdAt());
    }

}
