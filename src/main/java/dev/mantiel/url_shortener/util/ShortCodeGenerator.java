package dev.mantiel.url_shortener.util;

import java.security.SecureRandom;

public final class ShortCodeGenerator {

    private static final String ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final int CODE_LENGTH = 7;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private ShortCodeGenerator() {
        throw new IllegalStateException("This class cannot be instantiated.");
    }

    public static String generate() {
        final StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            final int alphabetLength = ALPHABET.length();
            final int randomInteger = SECURE_RANDOM.nextInt(alphabetLength);
            final char randomChar = ALPHABET.charAt(randomInteger);

            code.append(randomChar);
        }

        return code.toString();
    }

}
