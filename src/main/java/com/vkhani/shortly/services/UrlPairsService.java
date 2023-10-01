package com.vkhani.shortly.services;

import com.vkhani.shortly.dtos.UrlPairDto;
import com.vkhani.shortly.exceptions.BrokenUrlPairException;
import com.vkhani.shortly.exceptions.ShorteningUrlException;
import com.vkhani.shortly.models.UrlPair;
import com.vkhani.shortly.repositories.UrlPairsRepository;
import com.vkhani.shortly.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class UrlPairsService {
    @Autowired
    UrlPairsRepository repo;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public UrlPair createUrlPair(String longUrl) {
        String shortCode = produceShortCode();

        UrlPair newPair = new UrlPair(longUrl, shortCode, false);
        newPair.setId(sequenceGeneratorService.generateSequence(UrlPair.SEQUENCE_NAME));

        List<UrlPair> pairs = repo.findAll();
        pairs.add(newPair);
        repo.saveAll(pairs);

        return newPair;
    }

    public UrlPair createCustomUrlPair(String longUrl, String customShort) {
        UrlPair newPair = new UrlPair(longUrl, customShort, true);
        newPair.setId(sequenceGeneratorService.generateSequence(UrlPair.SEQUENCE_NAME));

        List<UrlPair> pairs = repo.findAll();
        pairs.add(newPair);
        repo.saveAll(pairs);

        return newPair;
    }

    public UrlPair getUrlPairByShort(String shortUrlPart) {
        return repo.findByShortcutCode(shortUrlPart);
    }

    public List<UrlPair> getUrlPairsByLong(String longUrl) {
        return repo.findPairsByLongURL(longUrl);
    }

    public List<UrlPair> getAll() {
        return repo.findAll();
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

            if (remainingAttempts == 0 && repo.findByShortcutCode(shortCode) != null)
                throw new ShorteningUrlException("Unable to generate unique short code");

        } while (repo.findByShortcutCode(shortCode) != null);

        return shortCode;
    }
}
