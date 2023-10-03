package com.vkhani.shortly.services;

import com.vkhani.shortly.dtos.UrlPairDto;
import com.vkhani.shortly.exceptions.BrokenUrlPairException;
import com.vkhani.shortly.exceptions.CodeTakenException;
import com.vkhani.shortly.exceptions.ShorteningUrlException;
import com.vkhani.shortly.models.UrlPair;
import com.vkhani.shortly.repositories.UrlPairsRepository;
import com.vkhani.shortly.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UrlPairsService {

    private final UrlPairsRepository repo;

    public UrlPair createUrlPair(String longUrl) {
        UrlPair newPair = new UrlPair(UUID.randomUUID(), longUrl, produceShortCode(), false);

        return repo.save(newPair);
    }

    public UrlPair createCustomUrlPair(String longUrl, String customShort) {
        if (getUrlPairByShort(customShort).isPresent()) {
            throw new CodeTakenException("Code " + customShort + " already taken.");
        }

        var existingPair = getUrlPairsByLong(longUrl);

        return existingPair.stream()
                .filter(x -> x.getShortcutCode().equals(customShort))
                .findFirst()
                .orElseGet(() -> {
                    UrlPair newPair = new UrlPair(UUID.randomUUID(), longUrl, customShort, true);

                    return repo.save(newPair);
                });
    }

    public Optional<UrlPair> getUrlPairByShort(String shortUrlPart) {
        return repo.findByShortcutCode(shortUrlPart);
    }

    public List<UrlPair> getUrlPairsByLong(String longUrl) {
        return repo.findPairsByLongURL(longUrl);
    }

    public static UrlPairDto convertUrlPairToDto(UrlPair urlPair) {
        if (urlPair.getLongURL() == null || urlPair.getShortcutCode() == null) {
            throw new BrokenUrlPairException("One of the URL pair elements turned out to be empty.");
        }

        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return new UrlPairDto(urlPair.getLongURL(), baseUrl + '/' + urlPair.getShortcutCode());
    }

    public UrlPair getOrCreatePair(String longUrl) {
        String url = longUrl.trim();
        List<UrlPair> existingPairs = getUrlPairsByLong(url);

        return existingPairs.stream()
                .filter(urlPair -> !urlPair.isCustom())
                .findFirst()
                .orElseGet(() -> createUrlPair(url));
    }

    private String produceShortCode() {
        String shortCode;
        int remainingAttempts = 5;

        do {
            shortCode = Utils.shortenURL();

            remainingAttempts--;

            if (remainingAttempts == 0 && repo.findByShortcutCode(shortCode).isPresent())
                throw new ShorteningUrlException("Unable to generate unique short code");

        } while (repo.findByShortcutCode(shortCode).isPresent());

        return shortCode;
    }
}
