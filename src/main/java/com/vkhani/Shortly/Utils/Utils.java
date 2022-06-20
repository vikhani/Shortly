package com.vkhani.Shortly.Utils;

import org.apache.commons.validator.routines.UrlValidator;

import java.util.*;
import java.util.stream.IntStream;

public class Utils {
    private Utils() {
    }

    public static boolean isUrlValid(String url) {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(url);
    }

    public static String shortenURL() {
        List<Character> possibleVals = new ArrayList<>();
        possibleVals.addAll(Arrays.stream(IntStream.rangeClosed(48, 57).toArray()).mapToObj(x -> (char) x).toList());
        possibleVals.addAll(Arrays.stream(IntStream.rangeClosed(65, 90).toArray()).mapToObj(x -> (char) x).toList());
        possibleVals.addAll(Arrays.stream(IntStream.rangeClosed(97, 122).toArray()).mapToObj(x -> (char) x).toList());

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
