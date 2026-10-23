package dev.mantiel.url_shortener.service;

import dev.mantiel.url_shortener.common.exception.InvalidFormatURLException;
import dev.mantiel.url_shortener.common.exception.NotFoundException;
import dev.mantiel.url_shortener.dto.CreateURLShortenerRequest;
import dev.mantiel.url_shortener.dto.URLShortenerResponse;
import dev.mantiel.url_shortener.repository.URLShortener;
import dev.mantiel.url_shortener.repository.URLShortenerRepository;
import dev.mantiel.url_shortener.util.ShortCodeGenerator;
import dev.mantiel.url_shortener.util.URLChecker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class URLShortenerService {

    private final URLShortenerRepository urlShortenerRepository;
    public URLShortenerService(final URLShortenerRepository urlShortenerRepository) {
        this.urlShortenerRepository = urlShortenerRepository;
    }

    private String generateSecureUniqueCode(final int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {
            final String code = ShortCodeGenerator.generate();
            if (!this.urlShortenerRepository.existsByCode(code)) {
                return code;
            }
        }

        throw new IllegalStateException("Unable to generate a unique short code.");
    }

    public URLShortenerResponse createShortUrl(final CreateURLShortenerRequest createURLShortenerRequest) {
        final String originalUrl = createURLShortenerRequest.originalUrl();
        if (!URLChecker.isValidUrl(originalUrl)) {
            throw new InvalidFormatURLException("The url must be valid.");
        }

        final String code = this.generateSecureUniqueCode(10);
        final URLShortener urlShortener = new URLShortener(code, originalUrl);
        this.urlShortenerRepository.save(urlShortener);
        return URLShortenerResponse.from(urlShortener);
    }

    public URLShortenerResponse getURLShortenerResponse(final String code) {
        final URLShortener shortener = this.urlShortenerRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("An URL with that code was not found."));

        return URLShortenerResponse.from(shortener);
    }

    public List<URLShortenerResponse> getUrls() {
        return this.urlShortenerRepository.findAll()
                .stream()
                .map(URLShortenerResponse::from)
                .toList();
    }

    @Transactional
    public URLShortenerResponse deleteShortURL(String code) {
        final URLShortener shortener = this.urlShortenerRepository.deleteByCode(code)
                .orElseThrow(() -> new NotFoundException("An URL with that code was not found."));

        return URLShortenerResponse.from(shortener);
    }

    public void deleteAllURLs() {
        this.urlShortenerRepository.deleteAll();
    }

}
