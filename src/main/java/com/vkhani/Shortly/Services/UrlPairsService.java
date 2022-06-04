package com.vkhani.Shortly.Services;

import com.vkhani.Shortly.Models.UrlPair;
import com.vkhani.Shortly.Repositories.UrlPairsRepository;
import com.vkhani.Shortly.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrlPairsService {
    @Autowired
    UrlPairsRepository repo;
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;
    public UrlPair createUrlPair(String longUrl){
        if(!Utils.isUrlValid(longUrl))
            throw new RuntimeException("Invalid URL.");
        UrlPair newPair = new UrlPair(longUrl, Utils.shortenURL(longUrl));
        newPair.setId(sequenceGeneratorService.generateSequence(UrlPair.SEQUENCE_NAME));

        List<UrlPair> pairs = repo.findAll();
        pairs.add(newPair);
        repo.saveAll(pairs);
        return newPair;
    }

    public UrlPair getUrlPairByShort(String shortUrlPart){
        return repo.findByShortenedUrl(shortUrlPart);
    }

    public UrlPair getUrlPairByLong(String longUrl){
        return repo.findPairByLongURL(longUrl);
    }
    public List<UrlPair> getAll(){
        return repo.findAll();
    }
}
