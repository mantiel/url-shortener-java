package dev.mantiel.url_shortener.controller;

import dev.mantiel.url_shortener.common.response.ApiResponse;
import dev.mantiel.url_shortener.dto.URLShortenerResponse;
import dev.mantiel.url_shortener.service.URLShortenerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class URLShortenerController {

    private final URLShortenerService urlShortenerService;
    public URLShortenerController(final URLShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse> getHealth() {
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "ok."));
    }

    @GetMapping("/{code}")
    public ResponseEntity<URLShortenerResponse> redirect(final @PathVariable String code) {
        final URLShortenerResponse response = this.urlShortenerService.getURLShortenerResponse(code);
        final String originalUrl = response.originalUrl();
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

}
