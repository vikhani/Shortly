package com.vkhani.Shortly.Utils;

import org.apache.commons.validator.routines.UrlValidator;

import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;

public class Utils {
    public static boolean isUrlValid(String url){
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }

    public static String shortenURL(String url){
        int leftLimit = 0;
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
