package dev.mantiel.url_shortener.repository;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "url_shortener")
public class URLShortener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 7)
    private String code;

    @Column(nullable = false)
    private String originalUrl;

    private Instant createdAt;

    public URLShortener() {

    }

    public URLShortener(String code, String originalUrl) {
        this(code, originalUrl, Instant.now());
    }

    public URLShortener(String code, String originalUrl, Instant createdAt) {
        this.code = code;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
    }

    public Integer id() {
        return this.id;
    }

    public String code() {
        return this.code;
    }

    public String originalUrl() {
        return this.originalUrl;
    }

    public Instant createdAt() {
        return this.createdAt;
    }
}



