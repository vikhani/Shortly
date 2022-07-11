package com.vkhani.Shortly.Utils;

import org.apache.commons.validator.routines.UrlValidator;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    private Utils() {
    }

    private static final int TARGET_STRING_LENGTH = 5;


    public static boolean isUrlValid(String url) {
        UrlValidator validator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.ALLOW_LOCAL_URLS);
        return validator.isValid(url);
    }


    public static String shortenURL(String url) throws NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-256");
        crypt.reset();
        crypt.update(url.getBytes(StandardCharsets.UTF_8));

        return new BigInteger(1, crypt.digest()).toString(16).substring(0, TARGET_STRING_LENGTH);
    }
}
