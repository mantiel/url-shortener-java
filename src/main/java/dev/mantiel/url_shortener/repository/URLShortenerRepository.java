package dev.mantiel.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface URLShortenerRepository extends JpaRepository<URLShortener, Integer> {

    Optional<URLShortener> findByCode(final String code);
    Optional<URLShortener> deleteByCode(final String code);

    boolean existsByCode(final String code);

}
