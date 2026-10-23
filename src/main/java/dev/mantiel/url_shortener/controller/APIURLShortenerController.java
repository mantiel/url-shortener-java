package dev.mantiel.url_shortener.controller;

import dev.mantiel.url_shortener.common.response.ApiResponse;
import dev.mantiel.url_shortener.dto.CreateURLShortenerRequest;
import dev.mantiel.url_shortener.dto.URLShortenerResponse;
import dev.mantiel.url_shortener.service.URLShortenerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/urls")
public class APIURLShortenerController {

    private final URLShortenerService urlShortenerService;
    public APIURLShortenerController(final URLShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity<URLShortenerResponse> createURL(final @Valid @RequestBody CreateURLShortenerRequest createURLShortenerRequest) {
        final URLShortenerResponse response = this.urlShortenerService.createShortUrl(createURLShortenerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{code}")
    public ResponseEntity<URLShortenerResponse> getURL(final @PathVariable @NotBlank String code) {
        final URLShortenerResponse response = this.urlShortenerService.getURLShortenerResponse(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<URLShortenerResponse> getURLs() {
        return this.urlShortenerService.getUrls();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<URLShortenerResponse> deleteURL(final @PathVariable @NotBlank String code) {
        final URLShortenerResponse response = this.urlShortenerService.deleteShortURL(code);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteURLs() {
        this.urlShortenerService.deleteAllURLs();
        return ResponseEntity.ok().body(new ApiResponse(HttpStatus.OK, "All codes was deleted."));
    }
}
