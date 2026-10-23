package dev.mantiel.url_shortener.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateURLShortenerRequest(
        @NotBlank
        String originalUrl
) {
}
