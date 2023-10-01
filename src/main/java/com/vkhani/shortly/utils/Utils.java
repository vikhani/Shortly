package com.vkhani.shortly.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {
    private Utils() {
    }

    public static boolean isUrlValid(String url) {
        String checkUrl = url;
        if(!checkUrl.contains("/")) {
            checkUrl = "https://" + checkUrl;
        }
        try {
            new URL(checkUrl).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
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
