package com.vkhani.shortly.repositories;

import com.vkhani.shortly.models.UrlPair;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UrlPairsRepository extends MongoRepository<UrlPair, String> {
    List<UrlPair> findPairsByLongURL(String longUrl);

    Optional<UrlPair> findByShortcutCode(String shortcutCode);
}
