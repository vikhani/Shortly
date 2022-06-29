package com.vkhani.Shortly.Utils;

import org.apache.commons.validator.routines.UrlValidator;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class Utils {
    private Utils() {
    }

    public static boolean isUrlValid(String url) {
        UrlValidator validator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.ALLOW_LOCAL_URLS);
        return validator.isValid(url);
    }

    public static String shortenURL() {
        List<Character> possibleVals = new ArrayList<>();
        // 0..9
        possibleVals.addAll(IntStream.rangeClosed(48, 57)
                .mapToObj(x -> (char) x)
                .collect(Collectors.toCollection(ArrayList::new)));
        // A..Z
        possibleVals.addAll(IntStream.rangeClosed(65, 90)
                .mapToObj(x -> (char) x)
                .collect(Collectors.toCollection(ArrayList::new)));
        // a..z
        possibleVals.addAll(IntStream.rangeClosed(97, 122)
                .mapToObj(x -> (char) x)
                .collect(Collectors.toCollection(ArrayList::new)));

        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            char randomLimitedInt = possibleVals.get(random.nextInt(possibleVals.size()));
            buffer.append(randomLimitedInt);
        }
        return buffer.toString();
    }
}
