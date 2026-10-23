package dev.mantiel.url_shortener.util;

import java.util.regex.Pattern;

public final class URLChecker {

    private static final Pattern PATTERN =
            Pattern.compile("^https?://(?:www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,63}\\b[-a-zA-Z0-9()@:%_+.~#?&/=]*$");

    private URLChecker() {
        throw new IllegalStateException("This class cannot be instantiated.");
    }

    public static boolean isValidUrl(final String url) {
        return PATTERN.matcher(url).matches();
    }

}
