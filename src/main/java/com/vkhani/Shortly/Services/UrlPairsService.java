package com.vkhani.Shortly.Services;

import com.vkhani.Shortly.Utils.Utils;
import com.vkhani.Shortly.Models.UrlPair;
import com.vkhani.Shortly.Repositories.UrlPairsRepository;
import com.vkhani.Shortly.dtos.UrlPairDto;
import com.vkhani.Shortly.exceptions.BrokenUrlPairException;
import com.vkhani.Shortly.exceptions.InvalidURLException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Service
public class UrlPairsService {
    @Autowired
    UrlPairsRepository repo;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    public UrlPair createUrlPair(String longUrl) {
        if (!Utils.isUrlValid(longUrl))
            throw new InvalidURLException("Invalid URL: " + longUrl);

        String shortCode;
        int maxAttempts = 5;
        int counter = 0;
        do {
            shortCode = Utils.shortenURL();
            counter++;

            if (counter == maxAttempts && repo.findByShortcutCode(shortCode) != null)
                throw new BrokenUrlPairException("Unable to generate unique short code");

        } while (repo.findByShortcutCode(shortCode) != null);

        UrlPair newPair = new UrlPair(longUrl, shortCode, false);
        newPair.setId(sequenceGeneratorService.generateSequence(UrlPair.SEQUENCE_NAME));

        List<UrlPair> pairs = repo.findAll();
        pairs.add(newPair);
        repo.saveAll(pairs);
        return newPair;
    }

    public UrlPair createCustomUrlPair(String longUrl, String customShort) {
        if (!Utils.isUrlValid(longUrl))
            throw new InvalidURLException("Invalid URL: " + longUrl);

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

        final String baseUrl =
                ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        return new UrlPairDto(urlPair.getLongURL(), baseUrl + '/' + urlPair.getShortcutCode());
    }
}
