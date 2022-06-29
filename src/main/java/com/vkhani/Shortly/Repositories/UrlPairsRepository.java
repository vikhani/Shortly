package com.vkhani.Shortly.Repositories;

import com.vkhani.Shortly.Models.UrlPair;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UrlPairsRepository extends MongoRepository<UrlPair, String> {
    List<UrlPair> findPairsByLongURL(String longUrl);

    UrlPair findByShortcutCode(String shortcutCode);
}
